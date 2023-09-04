package com.example.rudy.taskmanagerdemo.dto;

import com.example.rudy.taskmanagerdemo.domain.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    @NotBlank(message = "Title can't be empty.")
    @Size(min = 4, message = "Title must be at least 4 characters long.")
    private String title;
    @NotBlank(message = "Description can't be empty.")
    private String description;
    @NotNull(message = "You must select at least one option.")
    private Status status;
    private LocalDate createdOn;
    private LocalDate updatedOn;
}
