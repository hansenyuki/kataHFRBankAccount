package com.example.kataHFR.bank_account.builders;

import com.example.kataHFR.bank_account.enums.OperationType;
import com.example.kataHFR.bank_account.models.Account;
import com.example.kataHFR.bank_account.models.Operation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperationBuilder {

    private Long id;
    private LocalDateTime date;
    private OperationType type;
    private BigDecimal amount;
    private BigDecimal balanceUpdated;
    private Account account;

    public OperationBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public OperationBuilder date(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public OperationBuilder type(OperationType type) {
        this.type = type;
        return this;
    }

    public OperationBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public OperationBuilder balanceUpdated(BigDecimal balanceUpdated) {
        this.balanceUpdated = balanceUpdated;
        return this;
    }

    public OperationBuilder account(Account account) {
        this.account = account;
        return this;
    }

    public Operation build() {
        return new Operation(date, type, amount, balanceUpdated, account);
    }
}
