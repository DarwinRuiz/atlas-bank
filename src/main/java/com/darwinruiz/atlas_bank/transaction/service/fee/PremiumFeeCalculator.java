package com.darwinruiz.atlas_bank.transaction.service.fee;

import java.math.BigDecimal;

import com.darwinruiz.atlas_bank.account.model.AccountType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class PremiumFeeCalculator implements IFeeCalculator {

    @Override
    public Boolean supports(AccountType accountType) {
        return accountType == AccountType.PREMIUM;
    }

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return BigDecimal.ZERO;
    }

}
