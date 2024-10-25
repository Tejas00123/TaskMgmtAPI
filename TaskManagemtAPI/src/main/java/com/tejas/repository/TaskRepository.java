package com.tejas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejas.entity.Task;
import com.tejas.entity.Team;
import com.tejas.entity.User;
import com.tejas.enums.Priority;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom query methods if needed
	
	List<Task> findByDueDateBeforeAndCompletedFalse(Date dueDate);
    List<Task> findByDueDateBetweenAndCompletedFalse(Date startDate, Date endDate);
 // Custom query methods for filtering tasks

    // Find tasks by priority
    List<Task> findByPriority(Priority priority);

    // Find tasks by due date
    List<Task> findByDueDate(Date dueDate);

    // Find tasks by completion status
    List<Task> findByCompleted(boolean completed);

    // Find tasks assigned to a specific user
    List<Task> findByAssignedTo(User user);

    // Find tasks assigned to a specific team
    List<Task> findByTeam(Team team);

    // Find tasks by due date range and completion status
    List<Task> findByDueDateBetweenAndCompleted(Date startDate, Date endDate, boolean completed);

}
