package com.darwinruiz.atlas_bank.transactions.services;

import java.util.List;

import com.darwinruiz.atlas_bank.transactions.models.Transaction;

public interface ITransactionQueryService {
    public List<Transaction> getByAccountId(Long accountId);
}
