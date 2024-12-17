package com.example.kataHFR.bank_account.services.impl;


import com.example.kataHFR.bank_account.dao.AccountRepository;
import com.example.kataHFR.bank_account.dao.OperationRepository;
import com.example.kataHFR.bank_account.enums.OperationType;
import com.example.kataHFR.bank_account.exception.DepositException;
import com.example.kataHFR.bank_account.exception.WithdrawalException;
import com.example.kataHFR.bank_account.models.Account;
import com.example.kataHFR.bank_account.models.Operation;
import com.example.kataHFR.bank_account.services.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public Account getAccountById(Long accountId) throws RuntimeException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID " + accountId));
    }

    @Override
    public void makeWithdraw(Long accountId, BigDecimal amount) throws WithdrawalException {
        Objects.requireNonNull(amount, OperationType.WITHDRAWAL + " cannot be null.");
        if (amount.signum() <= 0) {
            throw new WithdrawalException(OperationType.WITHDRAWAL + " must be greater than zero.");
        }
        Account account = getAccountById(accountId);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new WithdrawalException("Insufficient balance for withdrawal :( ");
        }
        BigDecimal balanceUpdated = account.getBalance().subtract(amount);
        account.setBalance(balanceUpdated);
        accountRepository.save(account);
        Operation operation = new Operation(LocalDateTime.now(), OperationType.WITHDRAWAL, amount, balanceUpdated, account);
        operationRepository.save(operation);
    }

    @Override
    public void makeDeposit(Long accountId, BigDecimal amount) throws DepositException {
        Objects.requireNonNull(amount, OperationType.DEPOSIT + " cannot be null.");
        if (amount.signum() <= 0) {
            throw new DepositException(OperationType.DEPOSIT + " must be greater than zero.");
        }
        Account account = getAccountById(accountId);
        BigDecimal balanceUpdated = account.getBalance().add(amount);
        account.setBalance(balanceUpdated);
        accountRepository.save(account);
        Operation operation = new Operation(LocalDateTime.now(), OperationType.DEPOSIT, amount, balanceUpdated, account);
        operationRepository.save(operation);
    }
}