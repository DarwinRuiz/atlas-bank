package com.darwinruiz.atlas_bank.account.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = AccountStatus.ACTIVE;
        }

        if (this.balance == null) {
            this.balance = BigDecimal.ZERO;
        }
    }
}
