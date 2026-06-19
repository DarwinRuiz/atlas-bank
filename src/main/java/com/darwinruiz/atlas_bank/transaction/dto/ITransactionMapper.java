package com.darwinruiz.atlas_bank.transaction.dto;

import org.mapstruct.Mapper;

import com.darwinruiz.atlas_bank.transaction.model.Transaction;

@Mapper(componentModel = "spring")
public interface ITransactionMapper {
    TransactionResponse toResponse(Transaction transaction);
}
