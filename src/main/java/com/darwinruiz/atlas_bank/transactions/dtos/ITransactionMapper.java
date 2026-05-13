package com.darwinruiz.atlas_bank.transactions.dtos;

import org.mapstruct.Mapper;

import com.darwinruiz.atlas_bank.transactions.models.Transaction;

@Mapper(componentModel = "spring")
public interface ITransactionMapper {
    TransactionResponse toResponse(Transaction transaction);
}
