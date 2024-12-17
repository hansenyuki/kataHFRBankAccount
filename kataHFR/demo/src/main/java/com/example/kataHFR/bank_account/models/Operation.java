package com.example.kataHFR.bank_account.models;

import com.example.kataHFR.bank_account.builders.OperationBuilder;
import com.example.kataHFR.bank_account.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation {

    private Long id;
    private LocalDateTime date;
    private OperationType type;
    private BigDecimal amount;
    private BigDecimal balanceUpdated;
    private Account account;

    public Operation() {}

    public Operation(LocalDateTime date, OperationType type, BigDecimal amount, BigDecimal balanceUpdated, Account account) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balanceUpdated = balanceUpdated;
        this.account = account;
    }

    public Operation(Long id, LocalDateTime date, OperationType operation, BigDecimal amount, BigDecimal balanceUpdated, Account account) {
        this.id = id;
        this.date = date;
        this.type = operation;
        this.amount = amount;
        this.balanceUpdated = balanceUpdated;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalanceUpdated() {
        return balanceUpdated;
    }

    public void setBalanceUpdated(BigDecimal balanceUpdated) {
        this.balanceUpdated = balanceUpdated;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static OperationBuilder builder() {
        return new OperationBuilder();
    }
}
