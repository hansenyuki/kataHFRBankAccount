package com.example.kataHFR.bank_account.mapper;

import com.example.kataHFR.bank_account.builders.AccountBuilder;
import com.example.kataHFR.bank_account.dto.AccountDto;
import com.example.kataHFR.bank_account.models.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T23:48:38+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto accountToAccountDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId( account.getId() );
        accountDto.setBalance( account.getBalance() );

        return accountDto;
    }

    @Override
    public Account accountDtoToAccount(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        AccountBuilder account = Account.builder();

        account.id( accountDto.getId() );
        account.balance( accountDto.getBalance() );

        return account.build();
    }
}
