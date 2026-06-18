package com.darwinruiz.atlas_bank.transactions.controllers;

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

import com.darwinruiz.atlas_bank.transactions.dtos.ITransactionMapper;
import com.darwinruiz.atlas_bank.transactions.dtos.TransactionResponse;
import com.darwinruiz.atlas_bank.transactions.dtos.TransferRequest;
import com.darwinruiz.atlas_bank.transactions.models.Transaction;
import com.darwinruiz.atlas_bank.transactions.services.ITransactionQueryService;
import com.darwinruiz.atlas_bank.transactions.services.transfer.ITransferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final ITransferService transferService;
    private final ITransactionQueryService transactionQueryService;
    private final ITransactionMapper transactionMapper;

    public TransactionController(ITransferService transferService, ITransactionQueryService transactionQueryService,
            ITransactionMapper transactionMapper) {
        this.transferService = transferService;
        this.transactionQueryService = transactionQueryService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@Valid @RequestBody TransferRequest transferRequest) {
        Transaction transaction = transferService.execute(transferRequest.getFromAccountId(),
                transferRequest.getToAccountId(),
                transferRequest.getAmount(),
                transferRequest.getDescription());

        TransactionResponse transactionResponse = this.transactionMapper.toResponse(transaction);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionResponse);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccountId(@PathVariable Long id) {
        List<TransactionResponse> transactionResponses = transactionQueryService.getByAccountId(id)
                .stream()
                .map(this.transactionMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionResponses);
    }
}
