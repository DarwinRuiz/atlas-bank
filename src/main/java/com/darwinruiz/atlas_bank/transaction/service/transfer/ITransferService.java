package com.darwinruiz.atlas_bank.transaction.service.transfer;

import java.math.BigDecimal;

import com.darwinruiz.atlas_bank.transaction.model.Transaction;

public interface ITransferService {
    public Transaction execute(Long fromId, Long toId, BigDecimal amount, String description);
}
