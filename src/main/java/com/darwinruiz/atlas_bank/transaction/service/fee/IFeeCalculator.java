package com.darwinruiz.atlas_bank.transaction.service.fee;

import com.darwinruiz.atlas_bank.account.model.AccountType;

import java.math.BigDecimal;

public interface IFeeCalculator {

    public Boolean supports(AccountType accountType);

    public BigDecimal calculate(BigDecimal amount);

}
