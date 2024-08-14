package com.BankApplication.Service;

import com.BankApplication.DTO.BankAccountDto;
import com.BankApplication.Entities.BankAccountEntity;

import java.util.List;

public interface BankAccountService {
    String createAccount(BankAccountDto bankAccountDto);
    List<BankAccountDto> listAccounts(Long mobileNo);


}
