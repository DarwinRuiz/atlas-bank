package com.darwinruiz.atlas_bank.accounts.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.darwinruiz.atlas_bank.accounts.models.Account;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Account toEntity(CreateAccountRequest createAccountRequest);

    AccountResponse toResponse(Account account);
}
