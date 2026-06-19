package com.darwinruiz.atlas_bank.transaction.service;

import java.util.List;

import com.darwinruiz.atlas_bank.transaction.model.Transaction;

public interface ITransactionQueryService {
    public List<Transaction> getByAccountId(Long accountId);
}
