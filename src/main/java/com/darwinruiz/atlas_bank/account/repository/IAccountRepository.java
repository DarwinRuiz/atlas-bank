package com.darwinruiz.atlas_bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwinruiz.atlas_bank.account.model.Account;

public interface IAccountRepository extends JpaRepository<Account, Long> {

}
