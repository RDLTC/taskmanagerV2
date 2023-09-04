package com.example.rudy.taskmanagerdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
public @interface UsernameAlreadyExists {
    String message() default "Username Already Exists.";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
