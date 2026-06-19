package com.darwinruiz.atlas_bank.transaction.service.fee;

import java.math.BigDecimal;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class PremiumFeeCalculator implements IFeeCalculator {

    @Override
    public Boolean supports(String accountType) {
        return "PREMIUM".equals(accountType);
    }

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return BigDecimal.ZERO;
    }

}
