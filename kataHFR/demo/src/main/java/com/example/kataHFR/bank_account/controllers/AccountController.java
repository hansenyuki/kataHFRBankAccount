package com.example.kataHFR.bank_account.controllers;

import com.example.kataHFR.bank_account.services.impl.AccountServiceImpl;
import com.example.kataHFR.bank_account.services.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }
}