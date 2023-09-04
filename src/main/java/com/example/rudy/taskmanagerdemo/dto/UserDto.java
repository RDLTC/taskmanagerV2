package com.example.rudy.taskmanagerdemo.dto;

import com.example.rudy.taskmanagerdemo.validation.EmailAlreadyExists;
import com.example.rudy.taskmanagerdemo.validation.UsernameAlreadyExists;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@UsernameAlreadyExists
@EmailAlreadyExists
public class UserDto {
    private Long id;
    @Email(message = "Invalid email format.")
    @NotBlank(message = "Email cannot be empty or only whitespaces.")
    private String email;
    @NotBlank(message = "Password cannot be empty or only whitespaces.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;
    @NotBlank(message = "Username cannot be empty or only whitespaces.")
    @Size(min = 5, message = "Username must be at least 5 characters long.")
    private String userName;
    private String role;
    private List<TaskDto> tasks;
}
