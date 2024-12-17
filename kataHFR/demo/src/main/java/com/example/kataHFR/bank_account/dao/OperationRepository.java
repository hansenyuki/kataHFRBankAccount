package com.example.kataHFR.bank_account.dao;

import com.example.kataHFR.bank_account.enums.OperationType;
import com.example.kataHFR.bank_account.models.Account;
import com.example.kataHFR.bank_account.models.Operation;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OperationRepository {

    public OperationRepository() {}

    public Optional<List<Operation>> findByAccountId(Long accountId) {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(
                1L,
                LocalDateTime.now(),
                OperationType.DEPOSIT,
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(2000),
                new Account(1L, BigDecimal.valueOf(1000))
        ));
        return Optional.of(operations);
    }
    public void save(Operation operation) {
        System.out.println("operation successfully saved !!");
    }
}
