package com.example.rudy.taskmanagerdemo.mapper;

import com.example.rudy.taskmanagerdemo.domain.User;
import com.example.rudy.taskmanagerdemo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final TaskMapper taskMapper;
    
    public UserDto mapToDto(User user){
        UserDto userDto = UserDto.builder()
                                 .id(user.getId())
                                 .email(user.getEmail())
                                 .password(user.getPassword())
                                 .userName(user.getUsername())
                                 .tasks(user.getTasks().stream().map(taskMapper::mapToDto).toList())
                                 .role(user.getRol())
                                 .build();
        
        return userDto;
    }
    
    public User mapToUser(UserDto userDto){
        User user = User.builder()
                        .id(userDto.getId())
                        .email(userDto.getEmail())
                        .password(userDto.getPassword())
                        .userName(userDto.getUserName())
                        .tasks(userDto.getTasks().stream().map(taskMapper::mapToTask).toList())
                        .rol(userDto.getRole())
                        .build();
        return user;
    }
}
