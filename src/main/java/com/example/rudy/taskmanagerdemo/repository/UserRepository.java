package com.example.rudy.taskmanagerdemo.repository;

import com.example.rudy.taskmanagerdemo.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUserName(String userName);
    
    Optional<User> findByEmail(String email);
}
