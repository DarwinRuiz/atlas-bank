package com.darwinruiz.atlas_bank.transactions.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionResponse {
    private Long transactionId;

    private String type;

    private Long sourceAccountId;

    private Long targetAccountId;

    private BigDecimal amount;

    private BigDecimal fee;

    private String status;

    private LocalDateTime createdAt;
}
