package com.example.rudy.taskmanagerdemo.repository;

import com.example.rudy.taskmanagerdemo.domain.CompletedTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedTaskRepository extends JpaRepository<CompletedTask, Long>{
    
}
