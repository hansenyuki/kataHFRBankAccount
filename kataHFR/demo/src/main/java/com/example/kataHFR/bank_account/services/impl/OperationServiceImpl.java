package com.example.kataHFR.bank_account.services.impl;

import com.example.kataHFR.bank_account.dto.OperationDto;
import com.example.kataHFR.bank_account.exception.OperationException;
import com.example.kataHFR.bank_account.mapper.OperationMapper;
import com.example.kataHFR.bank_account.models.Operation;
import com.example.kataHFR.bank_account.dao.OperationRepository;
import com.example.kataHFR.bank_account.services.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository, OperationMapper operationMapper) {
        this.operationRepository = operationRepository;
        this.operationMapper = operationMapper;
    }

    @Override
    public List<OperationDto> getOperations(Long accountId) {
        List<Operation> operations = operationRepository.findByAccountId(accountId).get();

        if (operations == null || operations.isEmpty()) {
            throw new OperationException(accountId);
        }

        return operations.stream()
                .map(operationMapper::operationToOperationDto)
                .toList();
    }
}
