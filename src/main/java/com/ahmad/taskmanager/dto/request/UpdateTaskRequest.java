package com.ahmad.taskmanager.dto.request;

import com.ahmad.taskmanager.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime updatedAt;
}
