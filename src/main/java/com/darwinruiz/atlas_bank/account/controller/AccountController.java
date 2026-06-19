package com.darwinruiz.atlas_bank.account.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darwinruiz.atlas_bank.account.dto.AccountResponse;
import com.darwinruiz.atlas_bank.account.dto.CreateAccountRequest;
import com.darwinruiz.atlas_bank.account.dto.IAccountMapper;
import com.darwinruiz.atlas_bank.account.model.Account;
import com.darwinruiz.atlas_bank.account.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final IAccountService accountService;
    private final IAccountMapper accountMapper;

    public AccountController(IAccountService accountService, IAccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        Account account = this.accountMapper.toEntity(createAccountRequest);

        Account createdAccount = accountService.create(account);
        AccountResponse accountResponse = this.accountMapper.toResponse(createdAccount);

        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAll() {
        List<AccountResponse> accountResponses = accountService.getAll()
                .stream()
                .map(this.accountMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(accountResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable Long id) {
        Account account = accountService.getById(id);
        AccountResponse accountResponse = this.accountMapper.toResponse(account);
        return ResponseEntity.ok(accountResponse);
    }

}
