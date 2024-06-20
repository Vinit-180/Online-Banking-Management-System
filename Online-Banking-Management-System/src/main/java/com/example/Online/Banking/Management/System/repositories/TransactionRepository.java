package com.example.Online.Banking.Management.System.repositories;

import com.example.Online.Banking.Management.System.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
