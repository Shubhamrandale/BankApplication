package com.BankApplication.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private Integer TransactionId;
    private String modeOfTransaction;
    private Long paidTo;
    private Long receiverAccountNo;
    private Double amount;
    private LocalDateTime transactionDateTime;
    private String remarks;
    private Long paidFrom;
    private Long senderAccountNo;
}
