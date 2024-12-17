package com.example.kataHFR.bank_account.exception;

public class OperationException extends RuntimeException {
    public OperationException(Long accountId) {
        super("zero operation with the account " + accountId);
    }
}
