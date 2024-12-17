package com.example.kataHFR.bank_account.models;

import com.example.kataHFR.bank_account.builders.AccountBuilder;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private BigDecimal balance = BigDecimal.ZERO;

    public Account() {}

    public Account(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }
}