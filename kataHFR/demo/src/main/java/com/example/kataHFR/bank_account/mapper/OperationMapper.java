package com.example.kataHFR.bank_account.mapper;

import com.example.kataHFR.bank_account.dto.OperationDto;
import com.example.kataHFR.bank_account.models.Operation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface OperationMapper {

    OperationDto operationToOperationDto(Operation operation);
    Operation operationDtoToOperation(OperationDto operationDto);
}
