package com.darwinruiz.atlas_bank.transaction.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(Long accountId, BigDecimal balance, BigDecimal amount) {
        super("Insufficient funds in account with ID: " + accountId + ". Balance: " + balance
                + ", Attempted withdrawal: " + amount);
    }

}
