package com.example.kataHFR.bank_account.dao;

import com.example.kataHFR.bank_account.models.Account;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class AccountRepository {

    public AccountRepository() {}

    public Optional<Account> findById(Long accountId) {
        return Optional.of(new Account(1L, BigDecimal.valueOf(1000)));
    }

    public void save(Account account) {
        System.out.println("Account successfully saved !!");
    }
}
