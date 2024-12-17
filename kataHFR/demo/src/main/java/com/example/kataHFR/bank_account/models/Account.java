package com.example.kataHFR.bank_account.models;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Account {
    private Long id;
    private BigDecimal balance = BigDecimal.ZERO;

}