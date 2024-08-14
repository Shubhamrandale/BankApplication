package com.BankApplication.DTO;

import java.time.LocalDateTime;

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
