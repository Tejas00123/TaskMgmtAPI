package com.tejas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejas.entity.TaskDependency;

@Repository
public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {
    // Custom query methods if necessary
}
