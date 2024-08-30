package com.BankApplication.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="digital_bank_account")
public class DigitalBankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="digital_banking_id_sequence" )
    @SequenceGenerator(name = "digital_banking_id_sequence", sequenceName = "digital_banking_id_sequence",initialValue = 1001)
    @Column(name= "digital_banking_id")
    private String digitalBankingId;

    @Column(name= "mobile_no")
    private Long mobileNo;

    @Column(name= "account_no",insertable=false, updatable=false)
    private Long accountNo;

    @OneToOne
    @JoinColumn(name = "account_no", referencedColumnName = "account_no")
    private BankAccountEntity bankAccount;

    @Column(name= "account_type")
    private String accountType;

    @PrePersist
    public void generateDigitalBankingId() {
        this.digitalBankingId = "W_" +digitalBankingId;
    }
}
