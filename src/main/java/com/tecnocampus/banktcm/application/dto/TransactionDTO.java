package com.tecnocampus.banktcm.application.dto;

import com.tecnocampus.banktcm.domain.Transaction;

public class TransactionDTO {
    private String id;
    private String from;
    private String to;
    private double quantity;
    private Long timestamp;
    private String type;

    public TransactionDTO(Transaction transaction) {
        id=transaction.getId();
        from=transaction.getFrom().getIban();
        to=transaction.getTo().getIban();
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
