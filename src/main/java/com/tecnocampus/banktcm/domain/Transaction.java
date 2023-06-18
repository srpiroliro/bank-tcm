package com.tecnocampus.banktcm.domain;

import java.util.UUID;

// import com.tecnocampus.banktcm.application.dto.TransactionDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Transaction {
    @Id
    private String id = UUID.randomUUID().toString();

    private ETransactionTypes type;

    // private Account from;
    // private Account to;
    private String from;
    private String to;

    private double quantity;
    private Long timestamp = System.currentTimeMillis();

    // public Transaction(Account from, Account to, double quantity) throws Exception {
    public Transaction(String from, String to, double quantity) throws Exception {
        this.from=from;
        this.to=to;
        this.quantity=quantity;

        setTransactionType();
    }

    // public Transaction(TransactionDTO transaction) throws Exception {
    //     from=accountRepository.getAccountByIban(transaction.getFrom());
    //     to=accountRepository.getAccountByIban(transaction.getTo());
    //     quantity=transaction.getQuantity();        
    // }

    private void setTransactionType(){
        if(from == null || to == null)
            type=ETransactionTypes.CASH;
        else 
            type=ETransactionTypes.WIRE;
    }

    public String getId() {
        return id;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    // public Account getFrom() {
    //     return from;
    // }
    // public Account getTo() {
    //     return to;
    // }
    public double getQuantity() {
        return quantity;
    }
    public Long getTimestamp() {
        return timestamp;
    }
    public ETransactionTypes getType(){
        return type;
    }
    
}
