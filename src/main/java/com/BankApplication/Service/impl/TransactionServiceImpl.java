package com.BankApplication.Service.impl;

import com.BankApplication.DTO.TransactionDto;
import com.BankApplication.Entities.BankAccountEntity;
import com.BankApplication.Entities.DigitalBankAccountEntity;
import com.BankApplication.Entities.TransactionEntity;
import com.BankApplication.Exception.DigitalBankingException;
import com.BankApplication.Repository.BankAccountRepository;
import com.BankApplication.Repository.DigitalBankAccountRepository;
import com.BankApplication.Repository.TransactionRepository;
import com.BankApplication.Service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private DigitalBankAccountRepository digitalBankAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    @Transactional
    public String fundTransfer(TransactionDto transactionDto) {

        DigitalBankAccountEntity sendersDigitalBankAccount = digitalBankAccountRepository.findByMobileNoAndBankAccount_AccountNo(transactionDto.getPaidFrom(), transactionDto.getSenderAccountNo())
                .orElseThrow(() -> new DigitalBankingException("SENDERS_ACCOUNT_NOT_FOUND"));
        DigitalBankAccountEntity receiversDigitalBankAccount = digitalBankAccountRepository.findByMobileNoAndBankAccount_AccountNo(transactionDto.getPaidTo(), transactionDto.getReceiverAccountNo()).
                orElseThrow(() -> new DigitalBankingException("RECEIVERS_ACCOUNT_NOT_FOUND"));

        BankAccountEntity sendersBankAccount = sendersDigitalBankAccount.getBankAccount();
        BankAccountEntity receiversBankAccount = receiversDigitalBankAccount.getBankAccount();

        if(sendersBankAccount.getBalance() < transactionDto.getAmount()){
            throw new DigitalBankingException("INSUFFICIENT_FUNDS");
        }

        sendersBankAccount.setBalance(sendersBankAccount.getBalance() - transactionDto.getAmount());
        receiversBankAccount.setBalance(receiversBankAccount.getBalance() + transactionDto.getAmount());

        bankAccountRepository.save(sendersBankAccount);
        bankAccountRepository.save(receiversBankAccount);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setModeOfTransaction(transactionDto.getModeOfTransaction());
        transaction.setPaidFrom(transactionDto.getPaidFrom());
        transaction.setPaidTo(transactionDto.getPaidTo());
        transaction.setSenderBankAccount(sendersBankAccount);
        transaction.setReceiverBankAccount(receiversBankAccount);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionDateTime(LocalDateTime.now());
        transaction.setRemarks(transactionDto.getRemarks());

        transactionRepository.save(transaction);

        return "Fund transfer successful. Transaction ID :- " + transaction.getTransactionId();
    }

    @Override
    public List<TransactionDto> accountStatement(Long mobileNo) {
        return null;
    }

    public TransactionDto mapToDto(TransactionEntity transactionEntity){
        TransactionDto dto = modelMapper.map(transactionEntity, TransactionDto.class);
        return dto;
    }
}
