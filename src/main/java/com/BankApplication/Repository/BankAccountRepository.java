package com.BankApplication.Repository;

import com.BankApplication.Entities.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    List<BankAccountEntity> findByMobileNo(Long mobileNo);

    Optional<BankAccountEntity> findByMobileNoAndAccountNo(Long mobileNo, Long accountNo);

}
