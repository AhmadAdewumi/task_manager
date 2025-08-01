package com.ahmad.taskmanager.service;

import com.ahmad.taskmanager.dto.request.CreateTaskRequest;
import com.ahmad.taskmanager.dto.request.UpdateTaskRequest;
import com.ahmad.taskmanager.dto.response.TaskResponseDto;
import com.ahmad.taskmanager.exception.ResourceNotFoundException;
import com.ahmad.taskmanager.model.Task;
import com.ahmad.taskmanager.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDto createTask(CreateTaskRequest request) {
        log.info("Attempting to create a new task in task service method with body {}", request);
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setCreatedAt(request.getCreatedAt());

        taskRepository.save(task);
        log.info("Task created successfully!");
        return TaskResponseDto.from(task);
    }

    @Override
    public TaskResponseDto updateTask(UpdateTaskRequest request, UUID taskId) {
        log.info("Attempting to update task with ID: {}", taskId);
        Task taskToUpdate = fetchTaskByIdUtil(taskId);

        taskToUpdate.setTitle(request.getTitle());
        taskToUpdate.setDescription(request.getDescription());
        taskToUpdate.setStatus(request.getStatus());
        taskToUpdate.setUpdatedAt(request.getUpdatedAt());
        log.info("Task with ID: {} updated successfully!", taskId);
        return TaskResponseDto.from(taskToUpdate);
    }

    @Override
    public List<TaskResponseDto> listAllTasks() {
        log.info("Attempting to fetch all Tasks!");
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(TaskResponseDto :: from)
                .toList();
    }

    @Override
    public TaskResponseDto getTaskById(UUID taskId) {
        log.info("Attempting to retrieve task with ID: {}", taskId);
        Task task =  fetchTaskByIdUtil(taskId);

        return TaskResponseDto.from(task);
    }

    //Helper method to fetch tasks (DRY)
    private Task fetchTaskByIdUtil(UUID taskId){
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Task with ID: %d NOT FOUND!",taskId)));
    }

    @Override
    public void deleteTaskById(UUID taskId) {
        log.info("Attempting to delete task with ID: {}", taskId);
        fetchTaskByIdUtil(taskId);

        taskRepository.deleteById(taskId);
    }

    @Override
    public Page<TaskResponseDto> getTaskByStatus(String status, Pageable pageable){
           return taskRepository.findByStatus(status,pageable);
    }
}
