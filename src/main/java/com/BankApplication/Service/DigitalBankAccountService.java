package com.BankApplication.Service;

public interface DigitalBankAccountService {
    String linkAccount(Long mobileNo, Long accountNo);
    String linkAccount(Long mobileNo, Long accountNo,Integer OTP);
    Double checkBalance(Long mobileNo, Long accountNo);
}
