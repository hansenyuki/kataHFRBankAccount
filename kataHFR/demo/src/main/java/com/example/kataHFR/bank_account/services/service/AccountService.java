package com.example.kataHFR.bank_account.services.service;

import com.example.kataHFR.bank_account.exception.DepositException;
import com.example.kataHFR.bank_account.exception.WithdrawalException;
import com.example.kataHFR.bank_account.models.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account getAccountById(Long accountId) throws RuntimeException;
    void makeWithdraw(Long accountId, BigDecimal amount) throws WithdrawalException;
    void makeDeposit(Long accountId, BigDecimal amount) throws DepositException;
}