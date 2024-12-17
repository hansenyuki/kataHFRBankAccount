package com.example.kataHFR.bank_account.dao;

import com.example.kataHFR.bank_account.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}