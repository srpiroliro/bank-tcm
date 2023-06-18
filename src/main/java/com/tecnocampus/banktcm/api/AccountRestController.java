package com.tecnocampus.banktcm.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.tecnocampus.banktcm.application.AccountController;
import com.tecnocampus.banktcm.application.dto.AccountDTO;
import com.tecnocampus.banktcm.application.dto.TransactionDTO;


@RestController
public class AccountRestController {
    private AccountController accountController;

    public AccountRestController(AccountController accountController){
        this.accountController=accountController;
    }


    // TODO: verification


    @PostMapping("/users/{userId}/accounts")
    public AccountDTO createAccount(@PathVariable String userId, @RequestBody AccountDTO accountDTO) throws Exception {
        return accountController.createAccount(userId, accountDTO);
    }

    @GetMapping("/users/{userId}/accounts")
    public List<AccountDTO> getUserAccounts(@PathVariable String userId) throws Exception {
        return accountController.getAccountsByUser(userId);
    }

    @GetMapping("/users/{userId}/accounts/{accountId}")
    public AccountDTO getAccount(@PathVariable String userId, @PathVariable String accountId) throws Exception {
        return accountController.getAccount(accountId);
    }

    @DeleteMapping("/users/{userId}/accounts/{accountId}")
    public void deleteAccount(@PathVariable String userId, @PathVariable String accountId) throws Exception {
        accountController.deleteAccount(userId, accountId);
    }



    @PostMapping("/users/{userId}/accounts/{accountId}/deposit")
    public TransactionDTO deposit(@PathVariable String userId, @PathVariable String accountId, @RequestBody TransactionDTO transaction) throws Exception {
        return accountController.deposit(accountId, transaction.getQuantity());
    }

    @PostMapping("/users/{userId}/accounts/{accountId}/withdraw")
    public TransactionDTO withdraw(@PathVariable String userId, @PathVariable String accountId, @RequestBody TransactionDTO transaction) throws Exception {
        return accountController.withdraw(accountId, transaction.getQuantity());
    }

    @PostMapping("/users/{userId}/accounts/{accountId}/wire")
    public TransactionDTO wire(@PathVariable String userId, @PathVariable String accountId, @RequestBody TransactionDTO transaction) throws Exception {
        return accountController.wire(accountId, transaction.getFrom(), transaction.getQuantity()); 
    }
}