package com.darwinruiz.atlas_bank.transactions.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darwinruiz.atlas_bank.transactions.models.Transaction;
import com.darwinruiz.atlas_bank.transactions.services.ITransactionQueryService;
import com.darwinruiz.atlas_bank.transactions.services.ITransferService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final ITransferService transferService;
    private final ITransactionQueryService transactionQueryService;

    public TransactionController(ITransferService transferService, ITransactionQueryService transactionQueryService) {
        this.transferService = transferService;
        this.transactionQueryService = transactionQueryService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestParam Long fromId, @RequestParam Long toId,
            @RequestParam BigDecimal amount) {
        Transaction transaction = transferService.execute(fromId, toId, amount);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transaction);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable Long id) {
        List<Transaction> transactions = transactionQueryService.getByAccountId(id);
        return ResponseEntity.ok(transactions);
    }
}
