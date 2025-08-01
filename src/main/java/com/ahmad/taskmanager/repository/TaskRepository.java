package com.ahmad.taskmanager.repository;

import com.ahmad.taskmanager.dto.response.TaskResponseDto;
import com.ahmad.taskmanager.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Page<TaskResponseDto> findByStatus(String status, Pageable pageable);
}
