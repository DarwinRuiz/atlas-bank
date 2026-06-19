package com.darwinruiz.atlas_bank.transaction.service.fee;

import java.math.BigDecimal;

public interface IFeeCalculator {

    public Boolean supports(String accountType);

    public BigDecimal calculate(BigDecimal amount);

}
