package com.example.kataHFR.bank_account.services.service;

import com.example.kataHFR.bank_account.dto.OperationDto;

import java.util.List;

public interface OperationService {

    List<OperationDto> getOperations(Long accountId);
}
