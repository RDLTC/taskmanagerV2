package com.example.rudy.taskmanagerdemo.repository;

import com.example.rudy.taskmanagerdemo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
