package com.example.kataHFR.bank_account.controllers;

import com.example.kataHFR.bank_account.exception.DepositException;
import com.example.kataHFR.bank_account.models.Account;
import com.example.kataHFR.bank_account.services.impl.AccountServiceImpl;
import com.example.kataHFR.bank_account.services.service.AccountService;
import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable @NotNull Long id) {
        try {
            Account account = accountService.getAccountById(id);
            return ResponseEntity.ok(account);
        } catch (RuntimeException e) {
            log.error("Account not found with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<String> makeDeposit(@PathVariable Long id,
                                              @RequestParam(name = "amount") BigDecimal amount) {
        try {
            accountService.makeDeposit(id, amount);
            return ResponseEntity.ok("Deposit successful");
        } catch (DepositException e) {
            log.error("Deposit failed for account ID: {} with amount: {}", id, amount, e);
            String errorMessage = "Deposit failed: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (Exception e) {
            log.error("Unexpected error during deposit for account ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}