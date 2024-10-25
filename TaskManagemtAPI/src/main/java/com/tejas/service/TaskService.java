package com.tejas.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejas.entity.Task;
import com.tejas.entity.Team;
import com.tejas.entity.User;
import com.tejas.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    
    // Create or update a task
    public Task saveOrUpdateTask(Task task) {
        return taskRepository.save(task);
    }

    // Get a task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Delete a task by ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
    

    public Task completeTask(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (!optionalTask.isPresent()) {
            throw new IllegalArgumentException("Task not found");
        }
        
        Task task = optionalTask.get();
        
        // Check dependencies
        for (Task dependent : task.getDependentTasks()) {
            if (!dependent.isCompleted()) {
                throw new IllegalStateException("Cannot complete task with pending dependencies.");
            }
        }

        task.setCompleted(true); // Assuming you have a setCompleted method
        return taskRepository.save(task);
    }
    
    
 // Save method for creating or updating a task
    public Task save(Task task) {
        return taskRepository.save(task);
    }
    
    public Task toggleTaskCompletion(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (!optionalTask.isPresent()) {
            throw new IllegalArgumentException("Task not found");
        }
        
        Task task = optionalTask.get();
        
        // Check if the task can be marked as complete
        if (!task.isCompleted()) {
            // Ensure all dependencies are completed
            for (Task dependent : task.getDependentTasks()) {
                if (!dependent.isCompleted()) {
                    throw new IllegalStateException("Cannot complete task with pending dependencies.");
                }
            }
        }

        // Toggle the completed status
        task.setCompleted(!task.isCompleted());
        return taskRepository.save(task);
    }
    
    
    public Task assignTaskToUserOrTeam(Long taskId, User user, Team team) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        if (user != null) {
            task.setAssignedUser(user);
        } else if (team != null) {
            task.setAssignedTeam(team);
        }
        return taskRepository.save(task);
    }


}
