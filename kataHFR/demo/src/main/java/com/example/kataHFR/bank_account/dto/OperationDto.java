package com.example.kataHFR.bank_account.dto;

import com.example.kataHFR.bank_account.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperationDto {

    private Long id;
    private LocalDateTime date;
    private OperationType type;
    private BigDecimal amount;
    private BigDecimal balanceUpdated;
    private AccountDto account;

    public OperationDto() {}

    public OperationDto( Long id, OperationType type, BigDecimal amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
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

    public OperationType getOperation() {
        return type;
    }

    public void setOperation(OperationType type) {
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

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }
}
