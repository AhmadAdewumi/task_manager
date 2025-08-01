package com.ahmad.taskmanager.dto;

import com.ahmad.taskmanager.dto.request.CreateTaskRequest;
import com.ahmad.taskmanager.dto.response.ApiResponse;
import com.ahmad.taskmanager.dto.response.TaskResponseDto;
import com.ahmad.taskmanager.service.ITaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
//@RequestMapping("/api")
public class TaskController {
    private final ITaskService taskService;


    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<ApiResponse> getAllTasks() {
        try {
            List<TaskResponseDto> result = taskService.listAllTasks();
            return ResponseEntity.ok(new ApiResponse("Tasks Retrieved Successfully!", result));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error : UNABLE TO RETRIEVE TASKS!", INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<ApiResponse> getTaskById(@PathVariable UUID taskId) {
        try {
            TaskResponseDto result = taskService.getTaskById(taskId);
            return ResponseEntity.ok(new ApiResponse("Tasks Retrieved Successfully!", result));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<ApiResponse> createNewTask(@RequestBody CreateTaskRequest request) {
        try {
            TaskResponseDto task = taskService.createTask(request);
            return ResponseEntity.ok(new ApiResponse("Task created Successfully!", task));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable UUID taskId) {
        try {
            taskService.deleteTaskById(taskId);
            return ResponseEntity.ok(new ApiResponse("Task deleted Successfully!", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<ApiResponse> retrieveTaskByStatus(@RequestParam(required = false) String status,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int limit) {
        try {
            Pageable pageable = PageRequest.of(page, limit);

            Page<TaskResponseDto> tasks = taskService.getTaskByStatus(status, pageable);
            return ResponseEntity.ok(new ApiResponse("Task Retrieved Successfully!", tasks));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
