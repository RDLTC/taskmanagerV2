package com.example.rudy.taskmanagerdemo.validation;

import com.example.rudy.taskmanagerdemo.dto.UserDto;
import com.example.rudy.taskmanagerdemo.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.thymeleaf.util.StringUtils;


public record EmailValidator(UserService userService) implements ConstraintValidator<EmailAlreadyExists, UserDto>{

    @Override
    public void initialize(EmailAlreadyExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean isValid(UserDto user, ConstraintValidatorContext constraintValCont) {
        if(!StringUtils.isEmpty(user.getEmail()) && userService.emailAlreadyExists(user.getEmail())){
            constraintValCont.disableDefaultConstraintViolation();
            constraintValCont.buildConstraintViolationWithTemplate("Email Already in Existence")
                             .addPropertyNode("email")
                             .addConstraintViolation();
            return false;
        }
        return true;
    }
    
}
