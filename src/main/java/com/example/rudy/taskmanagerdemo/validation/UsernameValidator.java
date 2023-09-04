package com.example.rudy.taskmanagerdemo.validation;

import com.example.rudy.taskmanagerdemo.dto.UserDto;
import com.example.rudy.taskmanagerdemo.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.thymeleaf.util.StringUtils;

public record UsernameValidator(UserService userService) implements ConstraintValidator<UsernameAlreadyExists, UserDto>{

    @Override
    public void initialize(UsernameAlreadyExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean isValid(UserDto user, ConstraintValidatorContext constraintValCon) {
        if(!StringUtils.isEmpty(user.getUserName()) && userService.usernameAlreadyExists(user.getUserName())){
            constraintValCon.disableDefaultConstraintViolation();
            constraintValCon.buildConstraintViolationWithTemplate("Username Already Exists")
                    .addPropertyNode("userName")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
    
}
