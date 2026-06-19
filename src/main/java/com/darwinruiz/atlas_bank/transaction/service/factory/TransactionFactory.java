package com.darwinruiz.atlas_bank.transaction.service.factory;

import java.math.BigDecimal;

import com.darwinruiz.atlas_bank.transaction.model.Transaction;
import com.darwinruiz.atlas_bank.transaction.service.transfer.TransferContext;

public class TransactionFactory {

    public static Transaction createTransfer(TransferContext context, BigDecimal fee) {

        Transaction transaction = new Transaction();
        transaction.setType("TRANSFER");
        transaction.setCreateBy("SYSTEM");
        transaction.setSourceAccountId(context.fromAccount().getAccountId());
        transaction.setTargetAccountId(context.toAccount().getAccountId());
        transaction.setAmount(context.amount());
        transaction.setFee(fee);
        transaction.setStatus("EXECUTED");
        transaction.setDescription(context.description());

        return transaction;
    }
}
