package com.example.kataHFR.bank_account.dao;

import com.example.kataHFR.bank_account.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByAccountId(Long accountId);
}
