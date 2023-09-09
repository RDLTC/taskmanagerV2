package com.example.rudy.taskmanagerdemo.repository;

import com.example.rudy.taskmanagerdemo.domain.OngoingTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OngoingTaskRepository extends JpaRepository<OngoingTask, Long> {
    
}
