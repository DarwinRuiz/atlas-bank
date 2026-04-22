package com.darwinruiz.atlas_bank.transactions.services.fee;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class DefaultFeeCalculator implements IFeeCalculator {

    @Override
    public Boolean supports(String accountType) {
        // This calculator supports all account types as a default
        return true;
    }

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return BigDecimal.ZERO; // No fee for default calculator
    }

}
