package com.example.kataHFR.bank_account.mapper;

import com.example.kataHFR.bank_account.builders.OperationBuilder;
import com.example.kataHFR.bank_account.dto.OperationDto;
import com.example.kataHFR.bank_account.models.Operation;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T23:48:39+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class OperationMapperImpl implements OperationMapper {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public OperationDto operationToOperationDto(Operation operation) {
        if ( operation == null ) {
            return null;
        }

        OperationDto operationDto = new OperationDto();

        operationDto.setId( operation.getId() );
        operationDto.setDate( operation.getDate() );
        operationDto.setAmount( operation.getAmount() );
        operationDto.setBalanceUpdated( operation.getBalanceUpdated() );
        operationDto.setAccount( accountMapper.accountToAccountDto( operation.getAccount() ) );

        return operationDto;
    }

    @Override
    public Operation operationDtoToOperation(OperationDto operationDto) {
        if ( operationDto == null ) {
            return null;
        }

        OperationBuilder operation = Operation.builder();

        operation.id( operationDto.getId() );
        operation.date( operationDto.getDate() );
        operation.amount( operationDto.getAmount() );
        operation.balanceUpdated( operationDto.getBalanceUpdated() );
        operation.account( accountMapper.accountDtoToAccount( operationDto.getAccount() ) );

        return operation.build();
    }
}
