package com.example.Online.Banking.Management.System.models;

import jakarta.persistence.*;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long transactionId;

    private String type;
    private double amount;
    private int timestamp;

    public Transaction(){

    }

    public Transaction(int timestamp, double amount, String type) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.type = type;
    }

    public Long getTransactionId() {
        return transactionId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
