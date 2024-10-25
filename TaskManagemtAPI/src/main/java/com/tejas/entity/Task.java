package com.tejas.entity;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Date dueDate;

    // Many-to-One relationship with User (optional if user assignment is required)
    @ManyToOne
    private User assignedTo;

    // For dependencies, a task can have multiple dependent tasks
    @OneToMany
    private List<Task> dependencies;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;  // Ensure this field exists and is correctly mapped
    // Getters and Setters
    // ...
}

enum Priority {
    LOW,
    MEDIUM,
    HIGH
}
