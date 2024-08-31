package com.BankApplication.DTO;

import lombok.Data;

@Data
public class AccountLinkRequest {
    private Long accountNo;

    private Integer otp;
}
