package com.darwinruiz.atlas_bank.accounts.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountResponse {
    private Long accountId;

    private String accountNumber;

    private String ownerName;

    private String email;

    private String type;

    private BigDecimal balance;

    private String status;

    private LocalDateTime createdAt;
}
