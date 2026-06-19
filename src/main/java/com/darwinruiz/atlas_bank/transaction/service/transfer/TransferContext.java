package com.darwinruiz.atlas_bank.transaction.service.transfer;

import java.math.BigDecimal;

import com.darwinruiz.atlas_bank.account.model.Account;

public record TransferContext(Account fromAccount, Account toAccount, BigDecimal amount, String description) {

}
