package com.example.rudy.taskmanagerdemo.controller;

import com.example.rudy.taskmanagerdemo.dto.CompletedTaskDto;
import com.example.rudy.taskmanagerdemo.dto.OngoingTaskDto;
import com.example.rudy.taskmanagerdemo.dto.UserDto;
import com.example.rudy.taskmanagerdemo.service.CompletedTaskService;
import com.example.rudy.taskmanagerdemo.service.OngoingTaskService;
import com.example.rudy.taskmanagerdemo.service.UserService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasklist")
@RequiredArgsConstructor
public class TaskController {
    
    private final OngoingTaskService ongoingTaskService;
    private final CompletedTaskService completedTaskService;
    private final UserService userService;
    
    @GetMapping
    public String redirectToUserTasks(Authentication userAuthenticated){
        return "redirect:/tasklist/%s".formatted(userAuthenticated.getName());
    }
    
    @GetMapping("/{username}")
    @PreAuthorize("authentication.principal.username == #username")
    public String userTasksPage(@PathVariable String username, Model model){
        UserDto user = userService.findByUsername(username);
        model.addAttribute("ongoingTasks", user.getOngoingTasks());
        model.addAttribute("completedTasks", user.getCompletedTasks());
        return "tasksIndex";
    }
    
    @GetMapping("/{username}/addTask")
    @PreAuthorize("authentication.principal.username == #username")
    public String userAddTaskPage(@PathVariable String username, Model model){
        model.addAttribute("task", new OngoingTaskDto());
        return "addTask";
    }
    
    @GetMapping("{username}/modifyTask/{taskId}")
    @PreAuthorize("authentication.principal.username == #username")
    public String modifyTaskPage(@PathVariable String username, @PathVariable Long taskId, Model model){
        if(!ongoingTaskService.verifyTaskAsociatedWithUser(taskId, username)){
            throw new AccessDeniedException("Acceso no autorizado"); 
        }
        OngoingTaskDto task = ongoingTaskService.findTaskById(taskId);
        model.addAttribute("modifyTask", task);
        return "modifyTask";
    }
    
    @GetMapping("/{username}/dltOngTask/{taskId}")
    @PreAuthorize("authentication.principal.username == #username")
    public String deleteOngoingTask(@PathVariable String username, @PathVariable Long taskId){
        if(!ongoingTaskService.verifyTaskAsociatedWithUser(taskId, username)){
            throw new AccessDeniedException("Acceso no autorizado"); 
        }
        OngoingTaskDto task = ongoingTaskService.findTaskById(taskId);
        ongoingTaskService.deleteTask(task);
        return "redirect:/tasklist";
    }
    
    @GetMapping("{username}/dltCmpltTask/{taskId}")
    @PreAuthorize("authentication.principal.username == #username")
    public String deleteCompletedTask(@PathVariable String username, @PathVariable Long taskId){
        if(!ongoingTaskService.verifyTaskAsociatedWithUser(taskId, username)){
            throw new AccessDeniedException("Acceso no autorizado"); 
        }
        CompletedTaskDto task = completedTaskService.findTaskById(taskId);
        completedTaskService.deleteTask(task);
        return "redirect:/tasklist";
    }
    
    @PostMapping("/addTask")
    public String addTask(Authentication auth, @ModelAttribute("task") @Valid OngoingTaskDto task, Errors errors) throws Exception{
        if(errors.hasErrors()){
            return "addTask";
        }
        ongoingTaskService.addTask(task, auth.getName());
        return "redirect:/tasklist";
    }
    
    @PostMapping("/modifyTask/{taskId}")
    public String modifyTask(@PathVariable Long taskId, @ModelAttribute("modifyTask") @Valid OngoingTaskDto modifiedTask, Errors errors){
        if(errors.hasErrors()){
            return "modifyTask";
        }
        ongoingTaskService.modifyTask(modifiedTask, taskId);
        return "redirect:/tasklist";
    }
}
