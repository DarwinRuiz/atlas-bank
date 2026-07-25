package com.darwinruiz.atlas_bank.transaction.service.fee;

import java.math.BigDecimal;

import com.darwinruiz.atlas_bank.account.model.AccountType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CheckingFeeCalculator implements IFeeCalculator {

    @Override
    public Boolean supports(AccountType accountType) {
        return accountType == AccountType.CHECKING;
    }

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        // For example, let's say the fee is 1% of the amount
        return amount.multiply(new BigDecimal("0.015"));
    }

}
