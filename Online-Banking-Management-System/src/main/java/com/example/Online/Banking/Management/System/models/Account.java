package com.example.Online.Banking.Management.System.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.aot.generate.Generated;

import java.util.ArrayList;
import java.util.List;

@Entity
//@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    String name;
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double balance;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @OneToMany()
    private transient List<Transaction> transactions;

    public Account() {
        this.bankname = "Citibank"; // Setting the default value in the no-args constructor
        this.transactions=new ArrayList<Transaction>();
    }


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @Column(nullable = false)
    private Long userId;

    @Column(name="bank_name",nullable = false)
    private String bankname="Citibank";

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
//        transaction.setAccount(this);
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //    private Long accountNumber;


}
