package com.example.kataHFR.bank_account.services.service;

import com.example.kataHFR.bank_account.exceptions.DepositException;
import com.example.kataHFR.bank_account.exceptions.WithdrawalException;
import com.example.kataHFR.bank_account.models.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account getAccountById(Long accountId) throws DepositException;
    void makeWithdraw(Long accountId, BigDecimal amount) throws WithdrawalException;
    void makeDeposit(Long accountId, BigDecimal amount) throws DepositException;
}