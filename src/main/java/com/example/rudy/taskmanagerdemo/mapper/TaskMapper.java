package com.example.rudy.taskmanagerdemo.mapper;

import com.example.rudy.taskmanagerdemo.domain.Task;
import com.example.rudy.taskmanagerdemo.dto.TaskDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    
    public TaskDto mapToDto(Task task){
        TaskDto taskDto = TaskDto.builder()
                                 .id(task.getId())
                                 .title(task.getTitle())
                                 .description(task.getDescription())
                                 .status(task.getStatus())
                                 .createdOn(task.getCreatedOn())
                                 .updatedOn(task.getUpdatedOn())
                                 .build();
        return taskDto;
    }
    
    public Task mapToTask(TaskDto taskDto){
        Task task = Task.builder()
                        .id(taskDto.getId())
                        .title(taskDto.getTitle())
                        .description(taskDto.getDescription())
                        .status(taskDto.getStatus())
                        .createdOn(taskDto.getCreatedOn())
                        .updatedOn(taskDto.getUpdatedOn())
                        .build();
        return task;
    }
}
