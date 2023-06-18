package com.tecnocampus.banktcm.application.dto;

import com.tecnocampus.banktcm.domain.Account;

public class AccountDTO {
    private String id;
    private String iban;
    private double balance;

    public AccountDTO(){}
    public AccountDTO(Account account){
        id=account.getId();
        iban=account.getIban();
        balance=account.getBalance();
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