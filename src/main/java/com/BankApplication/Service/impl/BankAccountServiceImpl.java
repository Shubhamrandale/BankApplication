package com.BankApplication.Service.impl;

import com.BankApplication.DTO.BankAccountDto;
import com.BankApplication.Entities.BankAccountEntity;
import com.BankApplication.Exception.DigitalBankingException;
import com.BankApplication.Repository.BankAccountRepository;
import com.BankApplication.Service.BankAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String createAccount(BankAccountDto bankAccountDto) {
        try{
        BankAccountEntity bankAccountEntity = mapToEntity(bankAccountDto);
        BankAccountEntity savedEntity = bankAccountRepository.save(bankAccountEntity);
        return "Account Created Successfully. The Account Number :- "+savedEntity.getAccountNo();
        }
        catch (Exception e){
            throw new DigitalBankingException("NO ACCOUNTS FOUND");
        }
    }

    @Override
    public List<BankAccountDto> listAccounts(Long mobileNo) {
        List<BankAccountEntity> accounts = bankAccountRepository.findByMobileNo(mobileNo);
        if(accounts.isEmpty()){
            throw new DigitalBankingException("NO ACCOUNT IS FOUND WITH MOBILE NUMBER :"+mobileNo);
        }
        List<BankAccountDto> bankAccountDtos = accounts.stream().
                map(account -> mapToDto(account)).collect(Collectors.toList());
        return bankAccountDtos;
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
