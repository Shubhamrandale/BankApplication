package com.BankApplication.DTO;

import lombok.Data;
@Data
public class AccountRequest {

        private Long accountNo;

        // Getters and Setters
        public Long getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(Long accountNo) {
            this.accountNo = accountNo;
        }
    }












