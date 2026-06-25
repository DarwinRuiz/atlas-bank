package com.darwinruiz.atlas_bank.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.darwinruiz.atlas_bank.account.model.Account;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Primary
public class AuditableAccountService implements IAccountService {

    private final IAccountService delegate;

    public AuditableAccountService(@Qualifier("accountService") IAccountService delegate) {
        this.delegate = delegate;
    }

    @Override
    public Account create(Account account) {
        log.info("Creating account: {}", account);
        Account createdAccount = delegate.create(account);
        log.info("Account created: {}", createdAccount);

        return createdAccount;
    }

    @Override
    public List<Account> getAll() {
        log.info("Fetching all accounts");
        List<Account> accounts = delegate.getAll();
        log.info("Fetched accounts: {}", accounts);

        return accounts;
    }

    @Override
    public Account getById(Long id) {
        log.info("Fetching account by ID: {}", id);
        Account account = delegate.getById(id);
        log.info("Fetched account: {}", account);

        return account;
    }

}
