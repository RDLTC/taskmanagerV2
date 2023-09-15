package com.example.rudy.taskmanagerdemo.service;

import com.example.rudy.taskmanagerdemo.domain.OngoingTask;
import com.example.rudy.taskmanagerdemo.domain.User;
import com.example.rudy.taskmanagerdemo.dto.OngoingTaskDto;
import com.example.rudy.taskmanagerdemo.mapper.TaskMapper;
import com.example.rudy.taskmanagerdemo.mapper.UserMapper;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.rudy.taskmanagerdemo.repository.OngoingTaskRepository;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class OngoingTaskService {
    private final OngoingTaskRepository ongoingTaskRepository;
    
    private final UserService userService;
    private final CompletedTaskService completedTaskService;
    
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;
    
    public void addTask(OngoingTaskDto task, String username) throws Exception{
        User user = userMapper.mapToUser(userService.findByUsername(username));
        
        OngoingTask newTask = taskMapper.mapToOngoing(task);
        newTask.setUser(user);
        newTask.setCreatedOn(LocalDate.now());
        newTask.setStatus("ONGOING");
        
        ongoingTaskRepository.save(newTask);
    }
    
    public OngoingTaskDto findTaskById(Long taskId){
        OngoingTaskDto task = taskMapper.mapToOngoingDto(ongoingTaskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task with id "+taskId+" not found.")));
        return task;
    }
    
    public boolean verifyTaskAsociatedWithUser(Long taskId, String username){
        return ongoingTaskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task with id "+taskId+" not found."))
                                    .getUser().getUsername().equals(username);
    }
    
    public void modifyTask(OngoingTaskDto modifiedTask, Long taskId){
        OngoingTask taskToUpdate = ongoingTaskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task with id "+taskId+" not found."));
        
        taskToUpdate.setTitle(modifiedTask.getTitle());
        taskToUpdate.setDescription(modifiedTask.getDescription());
        taskToUpdate.setStatus(modifiedTask.getStatus());
        taskToUpdate.setUpdatedOn(LocalDate.now());
        taskToUpdate.setDoBefore(modifiedTask.getDoBefore());
        
        ongoingTaskRepository.save(taskToUpdate);
    }
    
    public void deleteTask(Long taskId){
        OngoingTaskDto taskExists = findTaskById(taskId);
        ongoingTaskRepository.deleteById(taskExists.getId());
    }
    
    public void finishedTask(Long modifiedTaskId, String username){
        OngoingTask finishedTask = taskMapper.mapToOngoing(findTaskById(modifiedTaskId));
        
        //deleteTask(modifiedTaskId);
        finishedTask.setUser(userMapper.mapToUser(userService.findByUsername(username)));
        
        completedTaskService.addTask(taskMapper.mapOngToCompleted(finishedTask));
    }
}
