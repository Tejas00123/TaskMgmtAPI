package com.tejas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejas.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	//finder methods
	
	List<Task> findByDueDateBeforeAndCompletedFalse(Date dueDate);
    List<Task> findByDueDateBetweenAndCompletedFalse(Date startDate, Date endDate);
}
