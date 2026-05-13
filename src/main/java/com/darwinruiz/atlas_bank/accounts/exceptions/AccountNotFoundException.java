package com.darwinruiz.atlas_bank.accounts.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long accountId) {
        super("Account not found with ID: " + accountId);
    }

}
