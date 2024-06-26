package com.example.Online.Banking.Management.System.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
//@AllArgsConstructor
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long transactionId;

    private String type;
    private double amount;

    private LocalDateTime timestamp;

    @Column(name = "source_account_id")
    @JsonProperty("sourceAccountId")
    private Long sourceAccountId;

    @Column(name = "target_account_id")
    @JsonProperty("targetAccountId")
    private Long targetAccountId;

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(Long targetAccountId) {
        targetAccountId = targetAccountId;
    }

    public Transaction(){

    }

    public Transaction( double amount, String type,Long sourceAccountId) {
        this.amount = amount;
        this.type = type;
        this.sourceAccountId=sourceAccountId;
    }
    @JsonCreator
    public Transaction( double amount, String type,Long sourceAccountId,Long targetAccountId) {
        this.amount = amount;
        this.type = type;
        this.sourceAccountId=sourceAccountId;
        this.targetAccountId=targetAccountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
