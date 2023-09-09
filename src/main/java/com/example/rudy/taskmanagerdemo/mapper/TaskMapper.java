package com.example.rudy.taskmanagerdemo.mapper;

import com.example.rudy.taskmanagerdemo.domain.CompletedTask;
import com.example.rudy.taskmanagerdemo.domain.OngoingTask;
import com.example.rudy.taskmanagerdemo.dto.CompletedTaskDto;
import com.example.rudy.taskmanagerdemo.dto.OngoingTaskDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    
    public OngoingTaskDto mapToOngoingDto(OngoingTask ongoingTask){
        OngoingTaskDto ongoingDto = OngoingTaskDto.builder()
                                 .id(ongoingTask.getId())
                                 .title(ongoingTask.getTitle())
                                 .description(ongoingTask.getDescription())
                                 .status(ongoingTask.getStatus())
                                 .createdOn(ongoingTask.getCreatedOn())
                                 .doBefore(ongoingTask.getDoBefore())
                                 .build();
        return ongoingDto;
    }
    
    public OngoingTask mapToOngoing(OngoingTaskDto ongoingDto){
        OngoingTask ongoingTask = OngoingTask.builder()
                                 .id(ongoingDto.getId())
                                 .title(ongoingDto.getTitle())
                                 .description(ongoingDto.getDescription())
                                 .status(ongoingDto.getStatus())
                                 .createdOn(ongoingDto.getCreatedOn())
                                 .doBefore(ongoingDto.getDoBefore())
                                 .build();
        return ongoingTask;
    }
    
    public CompletedTaskDto mapToCompletedDto(CompletedTask completedTask){
        CompletedTaskDto completedDto = CompletedTaskDto.builder()
                        .id(completedTask.getId())
                        .title(completedTask.getTitle())
                        .description(completedTask.getDescription())
                        .status(completedTask.getStatus())
                        .createdOn(completedTask.getCreatedOn())
                        .finishedOn(completedTask.getFinishedOn())
                        .build();
        return completedDto;
    }
    
    public CompletedTask mapToCompleted(CompletedTaskDto completedDto){
        CompletedTask completedTask = CompletedTask.builder()
                        .id(completedDto.getId())
                        .title(completedDto.getTitle())
                        .description(completedDto.getDescription())
                        .status(completedDto.getStatus())
                        .createdOn(completedDto.getCreatedOn())
                        .finishedOn(completedDto.getFinishedOn())
                        .build();
        return completedTask;
    }
}
