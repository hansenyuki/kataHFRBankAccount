package com.example.kataHFR.bank_account.controllers;

import com.example.kataHFR.bank_account.dto.OperationDto;
import com.example.kataHFR.bank_account.services.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;

    private static final Logger log = LoggerFactory.getLogger(OperationController.class);

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OperationDto>> getOperations(@PathVariable Long accountId) {
        try {
            List<OperationDto> operationDto = operationService.getOperations(accountId);
            return ResponseEntity.ok(operationDto);
        } catch (RuntimeException e) {
            log.error("Account not found with ID: {}", accountId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
