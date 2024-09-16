package com.BankApplication.Controller;

import com.BankApplication.DTO.AccountLinkRequest;
import com.BankApplication.DTO.AccountRequest;
import com.BankApplication.DTO.BankAccountDto;
import com.BankApplication.DTO.TransactionDto;
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

    @PostMapping("/linkAccount/{mobileNo}")
    public ResponseEntity<String> linkAccount
            (@PathVariable(value = "mobileNo") Long mobileNo , @RequestBody AccountRequest accountRequest){
        Long accountNo = accountRequest.getAccountNo();
        String linkedAccount = digitalBankAccountService.linkAccount(mobileNo, accountNo);
        return new ResponseEntity<>(linkedAccount, HttpStatus.OK);
    }
    @PostMapping("/linkAccount2/{mobileNo}")
    public ResponseEntity<String> linkAccount
            (@PathVariable(value = "mobileNo") Long mobileNo,@RequestBody AccountLinkRequest accountLinkRequest){
        Long accountNo = accountLinkRequest.getAccountNo();
        Integer otp = accountLinkRequest.getOtp();
        String linkedAccount = digitalBankAccountService.linkAccount(mobileNo, accountNo, otp);
        return new ResponseEntity<>(linkedAccount, HttpStatus.OK);
    }

    @GetMapping("/balance/{mobileNo}/")
    public ResponseEntity<Double> checkBalance
            (@PathVariable(value = "mobileNo" ) Long mobileNo, @RequestParam(name = "accountNo" ) Long accountNo){
        Double balance = digitalBankAccountService.checkBalance(mobileNo, accountNo);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PutMapping("/fundTransfer/")
    public ResponseEntity<String> fundTransfer(@RequestBody TransactionDto transactionDto){
        String fundTransfer = transactionService.fundTransfer(transactionDto);
        return new ResponseEntity<>(fundTransfer, HttpStatus.OK);
    }

    @GetMapping("/accountStatement/{mobileNo}")
    public ResponseEntity<List<TransactionDto>> accountStatement(@PathVariable(value = "mobileNo") Long mobileNo){
        List<TransactionDto> transactionDtos = transactionService.accountStatement(mobileNo);
        return new ResponseEntity<>(transactionDtos,HttpStatus.OK);
    }
}
