package com.tecnocampus.banktcm.application.dto;

import com.tecnocampus.banktcm.domain.Transaction;

public class TransactionDTO {
    private String id;
    private String from; // CHECK: IBAN or id?
    private String to; // CHECK: IBAN or id? 
    private double quantity;
    private Long timestamp;
    private String type;

    public TransactionDTO(Transaction transaction) {
        id=transaction.getId();
        // from=transaction.getFrom().getIban();
        // to=transaction.getTo().getIban();
        from=transaction.getFrom();
        to=transaction.getTo();
        quantity=transaction.getQuantity();
        timestamp=transaction.getTimestamp();
        type=transaction.getType().name();
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
    public double getQuantity() {
        return quantity;
    }
    public Long getTimestamp() {
        return timestamp;
    }
    public String getType(){
        return type;
    }
}
