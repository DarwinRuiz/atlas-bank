package com.darwinruiz.atlas_bank.transactions.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darwinruiz.atlas_bank.accounts.exceptions.AccountNotFoundException;
import com.darwinruiz.atlas_bank.accounts.models.Account;
import com.darwinruiz.atlas_bank.accounts.repositories.IAccountRepository;
import com.darwinruiz.atlas_bank.transactions.exceptions.AccountNotActiveException;
import com.darwinruiz.atlas_bank.transactions.exceptions.InsufficientFundsException;
import com.darwinruiz.atlas_bank.transactions.models.Transaction;
import com.darwinruiz.atlas_bank.transactions.repositories.ITransactionRepository;
import com.darwinruiz.atlas_bank.transactions.services.fee.IFeeCalculator;

@Service
public class TransferService implements ITransferService {
    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;
    private final List<IFeeCalculator> feeCalculators;

    public TransferService(IAccountRepository accountRepository, ITransactionRepository transactionRepository,
            List<IFeeCalculator> feeCalculators) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.feeCalculators = feeCalculators;
    }

    @Override
    @Transactional
    public Transaction execute(Long fromId, Long toId, BigDecimal amount) {
        // Buscar cuentas
        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new AccountNotFoundException(fromId));
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new AccountNotFoundException(toId));

        // Validar que la cuenta esté activa
        if (!"ACTIVE".equals(from.getStatus())) {
            throw new AccountNotActiveException(fromId, from.getStatus());
        }
        if (!"ACTIVE".equals(to.getStatus())) {
            throw new AccountNotActiveException(toId, to.getStatus());
        }

        // Validar fondos
        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(fromId, from.getBalance(), amount);
        }

        // Calcular comisión — hardcodeada
        BigDecimal fee = feeCalculators.stream()
                .filter(calculator -> calculator.supports(from.getType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró un calculador de comisiones para este tipo de cuenta"))
                .calculate(amount);

        // Actualizar saldos
        from.setBalance(from.getBalance().subtract(amount).subtract(fee));
        to.setBalance(to.getBalance().add(amount));
        accountRepository.save(from);
        accountRepository.save(to);

        // Crear transacción
        Transaction transaction = new Transaction();
        transaction.setType("TRANSFER");
        transaction.setSourceAccountId(fromId);
        transaction.setTargetAccountId(toId);
        transaction.setAmount(amount);
        transaction.setFee(fee);
        transaction.setStatus("EXECUTED");

        return transactionRepository.save(transaction);
    }

}
