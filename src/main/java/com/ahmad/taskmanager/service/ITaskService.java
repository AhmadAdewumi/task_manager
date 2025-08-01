package com.ahmad.taskmanager.service;

import com.ahmad.taskmanager.dto.request.CreateTaskRequest;
import com.ahmad.taskmanager.dto.request.UpdateTaskRequest;
import com.ahmad.taskmanager.dto.response.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ITaskService {
    TaskResponseDto createTask(CreateTaskRequest request);

    TaskResponseDto updateTask(UpdateTaskRequest request, UUID taskId);

    List<TaskResponseDto> listAllTasks();

    TaskResponseDto getTaskById(UUID taskId);

    void deleteTaskById(UUID taskId);

    Page<TaskResponseDto> getTaskByStatus(String status, Pageable pageable);
}
