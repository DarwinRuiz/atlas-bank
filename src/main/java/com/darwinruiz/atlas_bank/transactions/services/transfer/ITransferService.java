package com.darwinruiz.atlas_bank.transactions.services.transfer;

import java.math.BigDecimal;

import com.darwinruiz.atlas_bank.transactions.models.Transaction;

public interface ITransferService {
    public Transaction execute(Long fromId, Long toId, BigDecimal amount, String description);
}
