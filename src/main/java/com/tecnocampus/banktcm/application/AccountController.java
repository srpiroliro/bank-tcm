package com.tecnocampus.banktcm.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import com.tecnocampus.banktcm.application.dto.AccountDTO;
import com.tecnocampus.banktcm.application.dto.TransactionDTO;
import com.tecnocampus.banktcm.domain.Account;
import com.tecnocampus.banktcm.domain.Transaction;
import com.tecnocampus.banktcm.domain.User;
import com.tecnocampus.banktcm.persistence.AccountRepository;
import com.tecnocampus.banktcm.persistence.UserRepository;


@Controller
public class AccountController {
    private AccountRepository accountRepository;
    private UserRepository userRepository;

    public AccountController(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public AccountDTO createAccount(String userId, AccountDTO accountDTO) throws Exception {
        User user = userRepository.findById(userId).get();
        Account account = new Account(accountDTO, user);

        user.addAccount(account);

        userRepository.save(user);
        accountRepository.save(account);

        return new AccountDTO(account);
    }

    public AccountDTO getAccount(String accountId) throws Exception {
        return new AccountDTO(accountRepository.findById(accountId).get());
    }

    public List<AccountDTO> getAccountsByUser(String userId){
        return accountRepository.findByUserId(userId)
                                .stream()
                                .map(a->new AccountDTO(a))
                                .collect(Collectors.toList());
    }

    public void deleteAccount(String userId, String accountId) throws Exception {
        User user = userRepository.findById(userId).get();

        user.removeAccount(accountId);
        accountRepository.deleteById(accountId);

        userRepository.save(user);
    }


    public TransactionDTO withdraw(String accountId, double quantity) throws Exception {
        Account account = accountRepository.findById(accountId).get();
        Transaction transaction = account.withdraw(quantity);
        
        accountRepository.save(account);

        return new TransactionDTO(transaction);
    }

    public TransactionDTO deposit(String accountId, double quantity) throws Exception {
        Account account = accountRepository.findById(accountId).get();
        Transaction transaction = account.deposit(quantity);
        
        accountRepository.save(account);

        return new TransactionDTO(transaction);
    }


    // CHECK: reciever IBAN or id?
    public TransactionDTO wire(String accountId, String receiverAccountId, double quantity) throws Exception {
        Account account = accountRepository.findById(accountId).get();
        Account accountReceiver = accountRepository.findById(receiverAccountId).get();

        Transaction transaction = account.wire(accountReceiver, quantity);
        
        accountRepository.save(account);
        accountRepository.save(accountReceiver);

        return new TransactionDTO(transaction);
    }
}
