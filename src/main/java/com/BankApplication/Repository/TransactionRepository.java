package com.BankApplication.Repository;

import com.BankApplication.Entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

List<TransactionEntity> findByPaidFromOrPaidTo(Long paidFrom, Long paidTo);

}
