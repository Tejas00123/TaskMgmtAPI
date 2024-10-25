package com.tejas.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.entity.Task;
import com.tejas.entity.Team;
import com.tejas.entity.User;
import com.tejas.enums.Priority;
import com.tejas.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Create or Update Task
    @PostMapping 
    public ResponseEntity<Task> createOrUpdateTask(@RequestBody Task task) {
        Task savedTask = taskService.saveOrUpdateTask(task);
        return ResponseEntity.ok(savedTask);
    }

    // Get Task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all Tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Delete Task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.save(task);
        return ResponseEntity.ok(savedTask);
    }
    
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> toggleTaskCompletion(@PathVariable Long id) {
        Task updatedTask = taskService.toggleTaskCompletion(id);
        return ResponseEntity.ok(updatedTask);
    }

    
    // Endpoint to get sorted tasks
    @GetMapping("/sorted")
    public List<Task> getSortedTasks(@RequestParam String sortBy) {
        return taskService.getSortedTasks(sortBy);
    }

    // Endpoint to filter tasks by priority
    @GetMapping("/priority")
    public List<Task> getTasksByPriority(@RequestParam Priority priority) {
        return taskService.getTasksByPriority(priority);
    }

    // Endpoint to filter tasks by due date
    @GetMapping("/due-date")
    public List<Task> getTasksByDueDate(@RequestParam Date dueDate) {
        return taskService.getTasksByDueDate(dueDate);
    }

    // Endpoint to filter tasks by completion status
    @GetMapping("/completed")
    public List<Task> getTasksByCompletionStatus(@RequestParam boolean completed) {
        return taskService.getTasksByCompletionStatus(completed);
    }

    // Endpoint to filter tasks by assigned user
    @GetMapping("/assigned-user")
    public List<Task> getTasksByAssignedUser(@RequestParam User user) {
        return taskService.getTasksByAssignedUser(user);
    }

    // Endpoint to filter tasks by assigned team
    @GetMapping("/assigned-team")
    public List<Task> getTasksByAssignedTeam(@RequestParam Team team) {
        return taskService.getTasksByAssignedTeam(team);
    }
}
