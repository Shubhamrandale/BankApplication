package com.BankApplication.Repository;

import com.BankApplication.Entities.DigitalBankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DigitalBankAccountRepository extends JpaRepository<DigitalBankAccountEntity,String> {


}
