package com.darwinruiz.atlas_bank.transaction.validation;

import com.darwinruiz.atlas_bank.transaction.dto.TransferRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DifferentAccountsValidator implements ConstraintValidator<DifferentAccounts, TransferRequest> {

    @Override
    public boolean isValid(TransferRequest transferRequest, ConstraintValidatorContext context) {
        if (transferRequest.getFromAccountId() == null || transferRequest.getToAccountId() == null) {
            return true;
        }

        return !transferRequest.getFromAccountId().equals(transferRequest.getToAccountId());
    }

}
