package com.darwinruiz.atlas_bank.transaction.service.transfer;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darwinruiz.atlas_bank.account.exception.AccountNotFoundException;
import com.darwinruiz.atlas_bank.account.model.Account;
import com.darwinruiz.atlas_bank.account.repository.IAccountRepository;
import com.darwinruiz.atlas_bank.transaction.exception.AccountNotActiveException;
import com.darwinruiz.atlas_bank.transaction.exception.InsufficientFundsException;
import com.darwinruiz.atlas_bank.transaction.model.Transaction;
import com.darwinruiz.atlas_bank.transaction.repository.ITransactionRepository;
import com.darwinruiz.atlas_bank.transaction.service.factory.TransactionFactory;
import com.darwinruiz.atlas_bank.transaction.service.fee.IFeeCalculator;

@Service
public class TransferService extends TransactionProcesor<TransferContext> implements ITransferService {
    private final IAccountRepository accountRepository;
    private final List<IFeeCalculator> feeCalculators;

    public TransferService(IAccountRepository accountRepository, ITransactionRepository transactionRepository,
            List<IFeeCalculator> feeCalculators) {
        super(transactionRepository);
        this.accountRepository = accountRepository;
        this.feeCalculators = feeCalculators;
    }

    @Override
    @Transactional
    public Transaction execute(Long fromId, Long toId, BigDecimal amount, String description) {
        // Buscar cuentas
        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new AccountNotFoundException(fromId));
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new AccountNotFoundException(toId));

        return this.process(new TransferContext(from, to, amount, description));
    }

    @Override
    protected void validate(TransferContext context) {
        // Validar que la cuenta esté activa
        if (!"ACTIVE".equals(context.fromAccount().getStatus())) {
            throw new AccountNotActiveException(context.fromAccount().getAccountId(),
                    context.fromAccount().getStatus());
        }
        if (!"ACTIVE".equals(context.toAccount().getStatus())) {
            throw new AccountNotActiveException(context.toAccount().getAccountId(), context.toAccount().getStatus());
        }

        // Validar fondos
        if (context.fromAccount().getBalance().compareTo(context.amount()) < 0) {
            throw new InsufficientFundsException(context.fromAccount().getAccountId(),
                    context.fromAccount().getBalance(),
                    context.amount());
        }
    }

    @Override
    protected BigDecimal calculateFee(TransferContext context) {
        return feeCalculators.stream()
                .filter(calculator -> calculator.supports(context.fromAccount().getType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró un calculador de comisiones para este tipo de cuenta"))
                .calculate(context.amount());
    }

    @Override
    protected void execute(TransferContext context, BigDecimal fee) {
        // Actualizar saldos
        context.fromAccount().setBalance(context.fromAccount().getBalance().subtract(context.amount()).subtract(fee));
        context.toAccount().setBalance(context.toAccount().getBalance().add(context.amount()));
        accountRepository.save(context.fromAccount());
        accountRepository.save(context.toAccount());
    }

    @Override
    protected Transaction save(TransferContext context, BigDecimal fee) {
        Transaction transaction = TransactionFactory.createTransfer(context, fee);

        return transactionRepository.save(transaction);
    }

}
