package com.darwinruiz.atlas_bank.transaction.service.fee;

import java.math.BigDecimal;

import com.darwinruiz.atlas_bank.account.model.AccountType;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultFeeCalculator implements IFeeCalculator {

    @Override
    public Boolean supports(AccountType accountType) {
        // This calculator supports all account types as a default
        return true;
    }

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return BigDecimal.ZERO; // No fee for default calculator
    }

}
