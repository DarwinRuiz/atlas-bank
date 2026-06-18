package com.darwinruiz.atlas_bank.transactions.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER

    @Column(name = "source_account_id")
    private Long sourceAccountId;

    @Column(name = "target_account_id")
    private Long targetAccountId;

    private BigDecimal amount;

    private BigDecimal fee;

    private String status; // PENDING, EXECUTED, REJECTED

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createBy;

    private String description;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (this.status == null)
            this.status = "EXECUTED";
    }
}
