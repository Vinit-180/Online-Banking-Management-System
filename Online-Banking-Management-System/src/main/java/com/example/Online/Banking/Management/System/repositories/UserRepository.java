package com.example.Online.Banking.Management.System.repositories;

import com.example.Online.Banking.Management.System.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
 User findByUsername(String username);
}
