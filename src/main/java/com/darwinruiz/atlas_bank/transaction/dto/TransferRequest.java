package com.darwinruiz.atlas_bank.transaction.dto;

import java.math.BigDecimal;

import com.darwinruiz.atlas_bank.transaction.validation.DifferentAccounts;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@DifferentAccounts(message = "From account and To account must be different")
public class TransferRequest {

    @NotNull(message = "From account ID is required")
    private Long fromAccountId;

    @NotNull(message = "To account ID is required")
    private Long toAccountId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    private String description;

}
