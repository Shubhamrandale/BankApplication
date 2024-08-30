package com.BankApplication.Controller;

import com.BankApplication.DTO.BankAccountDto;
import com.BankApplication.Service.BankAccountService;
import com.BankApplication.Service.DigitalBankAccountService;
import com.BankApplication.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class AccountApi {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private DigitalBankAccountService digitalBankAccountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/saveAccount")
    public ResponseEntity<String> createAccount(@RequestBody BankAccountDto bankAccountDto) {
        String account = bankAccountService.createAccount(bankAccountDto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/bankList/{mobileNo}")
    public ResponseEntity<List<BankAccountDto>> listAccounts(@PathVariable (value = "mobileNo") Long mobileNo){
        List<BankAccountDto> bankAccountDtos = bankAccountService.listAccounts(mobileNo);
        return new ResponseEntity<>(bankAccountDtos, HttpStatus.OK);
    }


}
