package com.darwinruiz.atlas_bank.accounts.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darwinruiz.atlas_bank.accounts.models.Account;
import com.darwinruiz.atlas_bank.accounts.services.IAccountService;
import com.darwinruiz.atlas_bank.transactions.services.ITransactionQueryService;
import com.darwinruiz.atlas_bank.transactions.services.ITransferService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final IAccountService accountService;

    public AccountController(IAccountService accountService, ITransferService transferService,
            ITransactionQueryService transactionQueryService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        Account createdAccount = accountService.create(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        List<Account> accounts = accountService.getAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id) {
        Account account = accountService.getById(id);
        return ResponseEntity.ok(account);
    }

}
