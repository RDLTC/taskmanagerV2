package com.example.rudy.taskmanagerdemo;

import com.example.rudy.taskmanagerdemo.domain.CompletedTask;
import com.example.rudy.taskmanagerdemo.domain.OngoingTask;
import com.example.rudy.taskmanagerdemo.domain.User;
import com.example.rudy.taskmanagerdemo.repository.CompletedTaskRepository;
import com.example.rudy.taskmanagerdemo.repository.UserRepository;
import java.time.LocalDate;
import java.time.Month;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.rudy.taskmanagerdemo.repository.OngoingTaskRepository;

@SpringBootApplication
public class TaskmanagerDemoApplication implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final OngoingTaskRepository ongoingTaskRepository;
    private final CompletedTaskRepository completedTaskRepository;
    private final PasswordEncoder passwordEncoder;

    public TaskmanagerDemoApplication(UserRepository userRepository, OngoingTaskRepository ongoingTaskRepository, CompletedTaskRepository completedTaskRepository, 
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.ongoingTaskRepository = ongoingTaskRepository;
        this.passwordEncoder = passwordEncoder;
        this.completedTaskRepository = completedTaskRepository;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(TaskmanagerDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findAll().isEmpty()){
            User user = User.builder()
                            .email("admin@example.com")
                            .userName("admin")
                            .password(passwordEncoder.encode("admin96"))
                            .rol("ROLE_ADMIN")
                            .build();
            userRepository.save(user);
            
            OngoingTask task1 = new OngoingTask();
            task1.setTitle("Task1");
            task1.setDescription("This is task1");
            task1.setStatus("ONGOING");
            task1.setCreatedOn(LocalDate.now());
            task1.setDoBefore(LocalDate.of(2023, Month.SEPTEMBER, 30));
            task1.setUser(userRepository.findByUserName("admin").get());
            ongoingTaskRepository.save(task1);
            
            OngoingTask task2 = new OngoingTask();
            task2.setTitle("Task2");
            task2.setDescription("This is task2");
            task2.setStatus("ONGOING");
            task2.setCreatedOn(LocalDate.now());
            task2.setDoBefore(LocalDate.of(2023,Month.SEPTEMBER,30));
            task2.setUser(userRepository.findByUserName("admin").get());
            ongoingTaskRepository.save(task2);
            
            CompletedTask task3 = new CompletedTask();
            task3.setTitle("Task3 - Completed");
            task3.setDescription("This is task3 already completed");
            task3.setStatus("COMPLETED");
            task3.setCreatedOn(LocalDate.of(2023, Month.SEPTEMBER, 30));
            task3.setFinishedOn(LocalDate.now());
            task3.setUser(userRepository.findByUserName("admin").get());
            completedTaskRepository.save(task3);
        }
    }
}
