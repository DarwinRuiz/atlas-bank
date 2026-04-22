package com.darwinruiz.atlas_bank.transactions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwinruiz.atlas_bank.transactions.models.Transaction;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySourceAccountIdOrTargetAccountId(Long sourceId, Long targetId);
}
