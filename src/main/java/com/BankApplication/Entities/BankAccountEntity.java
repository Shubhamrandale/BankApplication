package com.BankApplication.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "bank_account")
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", initialValue = 51345678)
    @Column (name="account_no")
    private Long accountNo;

    @Column (name="bank_name")
    private String bankName;

    @Column (name="balance")
    private Double balance;

    @Column (name="bank_type")
    private String bankType;

    @Column (name="ifsc_code")
    private String ifscCode;

    @Column (name="opening_date")
    private LocalDate openingDate;

    @Column (name="mobile_no")
    private Long mobileNo;

    @OneToOne(mappedBy = "bankAccount")
    private DigitalBankAccountEntity digitalBankAccount;
}
