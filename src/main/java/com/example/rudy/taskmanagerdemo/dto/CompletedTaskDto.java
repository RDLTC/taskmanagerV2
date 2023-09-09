/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rudy.taskmanagerdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompletedTaskDto {
    private Long id;
    @NotBlank(message = "Title can't be empty.")
    @Size(min = 4, message = "Title must be at least 4 characters long.")
    private String title;
    @NotBlank(message = "Description can't be empty.")
    private String description;
    @NotNull(message = "You must select at least one option.")
    private String status;
    private LocalDate createdOn;
    private LocalDate finishedOn;
}
