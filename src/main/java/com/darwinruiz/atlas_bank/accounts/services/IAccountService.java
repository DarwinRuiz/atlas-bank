package com.darwinruiz.atlas_bank.accounts.services;

import java.util.List;

import com.darwinruiz.atlas_bank.accounts.models.Account;

public interface IAccountService {
    public Account create(Account account);

    public List<Account> getAll();

    public Account getById(Long id);
}
