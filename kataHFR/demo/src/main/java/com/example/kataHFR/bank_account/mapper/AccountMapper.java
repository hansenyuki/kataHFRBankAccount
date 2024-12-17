package com.example.kataHFR.bank_account.mapper;

import com.example.kataHFR.bank_account.dto.AccountDto;
import com.example.kataHFR.bank_account.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto accountToAccountDto(Account account);
    Account accountDtoToAccount(AccountDto accountDto);
}
