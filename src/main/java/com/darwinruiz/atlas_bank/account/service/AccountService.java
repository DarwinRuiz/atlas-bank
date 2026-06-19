package com.darwinruiz.atlas_bank.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.darwinruiz.atlas_bank.account.exception.AccountNotFoundException;
import com.darwinruiz.atlas_bank.account.model.Account;
import com.darwinruiz.atlas_bank.account.repository.IAccountRepository;

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
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

}
