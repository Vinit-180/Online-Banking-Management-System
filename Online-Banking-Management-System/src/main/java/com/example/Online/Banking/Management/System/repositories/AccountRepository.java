package com.example.Online.Banking.Management.System.repositories;


import com.example.Online.Banking.Management.System.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByUserId(Long userId);

}
