package com.example.rudy.taskmanagerdemo.service;

import com.example.rudy.taskmanagerdemo.domain.CompletedTask;
import com.example.rudy.taskmanagerdemo.dto.CompletedTaskDto;
import com.example.rudy.taskmanagerdemo.mapper.TaskMapper;
import com.example.rudy.taskmanagerdemo.repository.CompletedTaskRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CompletedTaskService {
    private final CompletedTaskRepository completedTaskRepo;
    private final TaskMapper taskMapper;
    
    public CompletedTaskDto findTaskById(Long taskId){
        CompletedTaskDto task = taskMapper.mapToCompletedDto(completedTaskRepo.findById(taskId).orElseThrow(() -> new NoSuchElementException("Completed Task with id "+taskId+" not found.")));
        return task;
    }
    
    public void deleteTask(Long taskId){
        CompletedTaskDto task = findTaskById(taskId);
        completedTaskRepo.deleteById(task.getId());
    }
    
    public void addTask(CompletedTask finished){
        finished.setStatus("FINISHED");
        finished.setFinishedOn(LocalDate.now());
        
        completedTaskRepo.save(finished);
    }
    
    public boolean verifyTaskAsociatedWithUser(Long taskId, String username){
        return completedTaskRepo.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task with id "+taskId+" not found."))
                                    .getUser().getUsername().equals(username);
    }
}
