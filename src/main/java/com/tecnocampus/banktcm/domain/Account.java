package com.tecnocampus.banktcm.domain;

import com.tecnocampus.banktcm.application.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;


@Getter
@Entity
public class Account {
    private static final String countryCode = "ES";

    private static final double MIN_DEPOSIT=20;
    private static final double MAX_DEPOSIT=1000;

    private static final double MIN_WITHDRAW=20;
    private static final double MAX_WITHDRAW=500;

    private static final double MIN_WIRE=1;
    private static final double MAX_WIRE=1_000_000;


    @ManyToOne
    private User user;

    @OneToMany
    private List<Transaction> transactions=new ArrayList<>();

    @Id
    private String id=UUID.randomUUID().toString();
    
    private String iban=generateIban();
    private double balance;

    private String generateIban(){
        return countryCode+"RANDOMGENERATEDIBAN"; // TODO: generate number from 
    }

    public Account(AccountDTO accountDTO, User user){
        this.user=user;
    }


    public Transaction wire(Account receiver, double quantity) throws Exception { // CHECK: pass id or object?
        validWire(quantity);

        updateBalance(-quantity);
        receiver.updateBalance(quantity);

        Transaction transaction = new Transaction(this, receiver, quantity);
        transactions.add(transaction);

        return transaction;
    }

    public Transaction deposit(double quantity) throws Exception {
        validDeposit(quantity);

        Transaction transaction = new Transaction(null, this, quantity);
        transactions.add(transaction);

        updateBalance(quantity);

        return transaction;
    }

    public Transaction withdraw(double quantity) throws Exception {
        validWithdraw(quantity);

        Transaction transaction = new Transaction(this, null, quantity);
        transactions.add(transaction);

        updateBalance(-quantity);

        return transaction;
    }

    public void updateBalance(double quantity) throws Exception {
        validQuantity(quantity);

        balance+=quantity;
    }

    private void validWire(double quantity) throws Exception {
        if(MIN_WIRE>quantity || MAX_WIRE>quantity)
            throw new Exception("Wire amount must be between "+MIN_WIRE+"$ and "+MAX_WIRE+"$.");
    }
    private void validDeposit(double quantity) throws Exception {
        if(MIN_DEPOSIT>quantity || MAX_DEPOSIT>quantity)
            throw new Exception("Deposit amount must be between "+MIN_DEPOSIT+"$ and "+MAX_DEPOSIT+"$.");
    }
    private void validWithdraw(double quantity) throws Exception {
        if(MIN_WITHDRAW>quantity || MAX_WITHDRAW>quantity)
            throw new Exception("Withdraw amount must be between "+MIN_WITHDRAW+"$ and "+MAX_WITHDRAW+"$.");
    }
    private void validQuantity(double quantity) throws Exception {
        if(balance+quantity<0)
            throw new Exception("Not enough balance!");
    }
    

    public String getId() {
        return id;
    }
    public String getIban() {
        return iban;
    }
    public double getBalance(){
        return balance;
    }
}