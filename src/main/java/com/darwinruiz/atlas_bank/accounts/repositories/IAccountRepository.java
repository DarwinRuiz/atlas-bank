package com.darwinruiz.atlas_bank.accounts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwinruiz.atlas_bank.accounts.models.Account;

public interface IAccountRepository extends JpaRepository<Account, Long> {

}
