package com.darwinruiz.atlas_bank.accounts.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darwinruiz.atlas_bank.accounts.models.Account;
import com.darwinruiz.atlas_bank.accounts.repositories.IAccountRepository;

@Service
public class AccountService implements IAccountService {
    private final IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

}
