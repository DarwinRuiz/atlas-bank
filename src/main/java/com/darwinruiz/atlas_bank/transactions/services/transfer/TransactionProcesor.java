package com.darwinruiz.atlas_bank.transactions.services.transfer;

import java.math.BigDecimal;

import org.springframework.transaction.annotation.Transactional;

import com.darwinruiz.atlas_bank.transactions.models.Transaction;
import com.darwinruiz.atlas_bank.transactions.repositories.ITransactionRepository;

public abstract class TransactionProcesor<C> {

    protected final ITransactionRepository transactionRepository;

    public TransactionProcesor(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction process(C context) {
        validate(context);
        BigDecimal fee = calculateFee(context);
        execute(context, fee);
        return save(context, fee);
    }

    protected abstract void validate(C context);

    protected abstract BigDecimal calculateFee(C context);

    protected abstract void execute(C context, BigDecimal fee);

    protected abstract Transaction save(C context, BigDecimal fee);
}
