package com.BankApplication.Repository;

import com.BankApplication.Entities.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

}
