package com.example.rudy.taskmanagerdemo.service;

import com.example.rudy.taskmanagerdemo.domain.User;
import com.example.rudy.taskmanagerdemo.dto.UserDto;
import com.example.rudy.taskmanagerdemo.mapper.UserMapper;
import com.example.rudy.taskmanagerdemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService{
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    
    public void registerUser(UserDto user){
        User newUser = User.builder()
                        .email(user.getEmail())
                        .userName(user.getUserName())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .rol("USER")
                        .build();
        userRepository.save(newUser);
    }
    
    public UserDto findByUsername(String username){
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username "+username+" not found."));
        return userMapper.mapToDto(user);
    }
    
    public boolean usernameAlreadyExists(String username){
        return userRepository.findAll().stream().anyMatch(user -> user.getUsername().equals(username));
    }
    
    public boolean emailAlreadyExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}
