package com.darwinruiz.atlas_bank.transactions.services.transfer;

import java.math.BigDecimal;

import com.darwinruiz.atlas_bank.accounts.models.Account;

public record TransferContext(Account fromAccount, Account toAccount, BigDecimal amount, String description) {

}
