package com.example.kataHFR.bank_account.services.impl;


import com.example.kataHFR.bank_account.dao.AccountRepository;
import com.example.kataHFR.bank_account.exceptions.DepositException;
import com.example.kataHFR.bank_account.exceptions.WithdrawalException;
import com.example.kataHFR.bank_account.models.Account;
import com.example.kataHFR.bank_account.services.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountById(Long accountId) throws DepositException {
        //todo
        return null;
    }

    @Override
    public void makeWithdraw(Long accountId, BigDecimal amount) throws WithdrawalException {
        //todo
    }

    @Override
    public void makeDeposit(Long accountId, BigDecimal amount) throws DepositException {
        //todo
    }
}