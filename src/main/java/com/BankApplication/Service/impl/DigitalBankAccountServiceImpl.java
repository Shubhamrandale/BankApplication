package com.BankApplication.Service.impl;

import com.BankApplication.DTO.DigitalBankAccountDto;
import com.BankApplication.Entities.BankAccountEntity;
import com.BankApplication.Entities.DigitalBankAccountEntity;
import com.BankApplication.Exception.DigitalBankingException;
import com.BankApplication.Repository.BankAccountRepository;
import com.BankApplication.Repository.DigitalBankAccountRepository;
import com.BankApplication.Service.DigitalBankAccountService;
import com.BankApplication.utils.OTPUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DigitalBankAccountServiceImpl implements DigitalBankAccountService
{
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private DigitalBankAccountRepository digitalBankAccountRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OTPUtility otpUtility;

    @Override
    public String linkAccount(Long mobileNo, Long accountNo) {

        BankAccountEntity bankAccount = bankAccountRepository.findByMobileNoAndAccountNo(mobileNo, accountNo).
                orElseThrow(() -> new DigitalBankingException("NO ACCOUNT FOUND"));
        DigitalBankAccountEntity digitalBankAccount = new DigitalBankAccountEntity();
        digitalBankAccount.setMobileNo(mobileNo);
        digitalBankAccount.setBankAccount(bankAccount);
        digitalBankAccount.setAccountType(bankAccount.getBankType());

        digitalBankAccountRepository.save(digitalBankAccount);

        return "Account Linked Successfully. Digital Banking Id :- "+digitalBankAccount.getDigitalBankingId();
    }

    @Override
    public String linkAccount(Long mobileNo, Long accountNo, Integer OTP) {
        BankAccountEntity bankAccount = bankAccountRepository.findByMobileNoAndAccountNo(mobileNo, accountNo).
                orElseThrow(() -> new DigitalBankingException("NO ACCOUNT FOUND"));
        if(!OTP.equals(otpUtility.sendOTP())){
            throw new DigitalBankingException("OTP DOES NOT MATCH");
        }
        DigitalBankAccountEntity digitalBankAccount = new DigitalBankAccountEntity();
        digitalBankAccount.setMobileNo(mobileNo);
        digitalBankAccount.setBankAccount(bankAccount);
        digitalBankAccount.setAccountType(bankAccount.getBankType());
        digitalBankAccountRepository.save(digitalBankAccount);
        return "Account Linked Successfully with OTP verification . DigitalBanking Id :- "+digitalBankAccount.getDigitalBankingId();
    }

    @Override
    public Double checkBalance(Long mobileNo, Long accountNo) {
        return null;
    }

    public DigitalBankAccountDto mapToDto(DigitalBankAccountEntity digitalBankAccountEntity){
        DigitalBankAccountDto digitalBankAccountDto = mapper.map(digitalBankAccountEntity, DigitalBankAccountDto.class);
        return digitalBankAccountDto;
    }

    public DigitalBankAccountEntity mapToEntity(DigitalBankAccountDto digitalBankAccountDto){
        DigitalBankAccountEntity digitalBankAccountEntity = mapper.map(digitalBankAccountDto,DigitalBankAccountEntity.class);
        return digitalBankAccountEntity;
    }


}
