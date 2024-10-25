package com.tejas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejas.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom query methods if needed
}
