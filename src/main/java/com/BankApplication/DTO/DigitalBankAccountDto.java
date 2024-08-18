package com.BankApplication.DTO;

import lombok.Data;

@Data
public class DigitalBankAccountDto {
    private String digitalBankingId;
    private Long mobileNo;
    private Long accountNo;
    private String accountType;
}
