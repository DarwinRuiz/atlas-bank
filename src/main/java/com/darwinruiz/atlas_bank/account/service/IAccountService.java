package com.darwinruiz.atlas_bank.account.service;

import java.util.List;

import com.darwinruiz.atlas_bank.account.model.Account;

public interface IAccountService {
    public Account create(Account account);

    public List<Account> getAll();

    public Account getById(Long id);
}
