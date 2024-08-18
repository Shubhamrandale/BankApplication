package com.BankApplication.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BankAccountDto {
    private Long accountNo;
    private String bankName;
    private Double balance;
    private String bankType;
    private String ifscCode;
    private LocalDate openingDate;
    private Long mobileNo;
}
