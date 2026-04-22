package com.darwinruiz.atlas_bank.transactions.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darwinruiz.atlas_bank.transactions.models.Transaction;
import com.darwinruiz.atlas_bank.transactions.repositories.ITransactionRepository;

@Service
public class TransactionQueryService implements ITransactionQueryService {
    private final ITransactionRepository transactionRepository;

    public TransactionQueryService(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getByAccountId(Long accountId) {
        return transactionRepository
                .findBySourceAccountIdOrTargetAccountId(accountId, accountId);
    }
}
