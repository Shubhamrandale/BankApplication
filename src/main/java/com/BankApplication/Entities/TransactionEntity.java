package com.BankApplication.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "transaction_id_sequence")
    @SequenceGenerator(name = "transaction_id_sequence",sequenceName = "transaction_id_sequence", initialValue = 1234567)
    @Column(name="transaction_id")
    private Integer TransactionId;

    @Column(name="mode_of_transaction")
    private String modeOfTransaction;

    @Column(name="paid_to")
    private Long paidTo;

    @ManyToOne
    @JoinColumn (name="receiver_account_no", referencedColumnName = "account_no")
    private BankAccountEntity receiverBankAccount;

    @Column(name="amount")
    private Double amount;

    @Column(name="transaction_date_time")
    private LocalDateTime transactionDateTime;

    @Column(name="remarks")
    private String remarks;

    @Column(name="paid_from")
    private Long paidFrom;

    @ManyToOne
    @JoinColumn(name="sender_account_no",referencedColumnName = "account_no")
    private BankAccountEntity senderBankAccount;

}
