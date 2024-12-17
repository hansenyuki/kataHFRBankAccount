package com.example.kataHFR.bank_account.builders;

import com.example.kataHFR.bank_account.models.Account;

import java.math.BigDecimal;

public class AccountBuilder {

    private Long id;
    private BigDecimal balance;

    public AccountBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        return new Account(id, balance);
    }
}
