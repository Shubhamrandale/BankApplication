package com.BankApplication.Service;

import com.BankApplication.DTO.TransactionDto;

import java.util.List;

public interface TransactionService {

    String fundTransfer(TransactionDto transactionDto);
    List<TransactionDto> accountStatement(Long mobileNo);
}
