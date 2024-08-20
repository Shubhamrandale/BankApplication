package com.BankApplication.Repository;

import com.BankApplication.Entities.DigitalBankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitalBankAccountRepository extends JpaRepository<DigitalBankAccountEntity,String> {

    Optional<DigitalBankAccountEntity> findByMobileNoAndBankAccount_AccountNo(Long mobileNo, Long accountNo);
}
