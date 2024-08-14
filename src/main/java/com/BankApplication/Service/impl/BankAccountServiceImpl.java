package com.BankApplication.Service.impl;

import com.BankApplication.DTO.BankAccountDto;
import com.BankApplication.Entities.BankAccountEntity;
import com.BankApplication.Repository.BankAccountRepository;
import com.BankApplication.Service.BankAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String createAccount(BankAccountDto bankAccountDto) {
        BankAccountEntity bankAccountEntity = mapToEntity(bankAccountDto);
        BankAccountEntity savedEntity = bankAccountRepository.save(bankAccountEntity);
        return "Account Created Successfully. The Account Number :- "+savedEntity.getAccountNo();
    }

    @Override
    public List<BankAccountDto> listAccounts(Long mobileNo) {
        return null;
    }

    public BankAccountDto mapToDto(BankAccountEntity bankAccountEntity){
        BankAccountDto bankAccountDto = new BankAccountDto();
        BankAccountDto accountDto = mapper.map(bankAccountEntity, BankAccountDto.class);
        return accountDto;
    }

    public BankAccountEntity mapToEntity(BankAccountDto bankAccountDto){
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        BankAccountEntity accountEntity = mapper.map(bankAccountDto, BankAccountEntity.class);
        return accountEntity;
    }
}
