package com.darwinruiz.atlas_bank.transaction.service.fee;

import java.math.BigDecimal;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CheckingFeeCalculator implements IFeeCalculator {

    @Override
    public Boolean supports(String accountType) {
        return "CHECKING".equalsIgnoreCase(accountType);
    }

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        // For example, let's say the fee is 1% of the amount
        return amount.multiply(new BigDecimal("0.015"));
    }

}
