package com.tejas.entity;
import java.util.Date;
import java.util.List;

import com.tejas.enums.Priority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @Enumerated(EnumType.STRING) // Make sure to import the correct Priority enum
    private Priority priority; // This should refer to the custom Priority enum

    private Date dueDate;

    @ManyToOne
    private User assignedTo;

    @OneToMany
    private List<Task> dependencies;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany
    @JoinTable(
        name = "task_dependencies",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "dependent_task_id")
    )
    private List<Task> dependentTasks;

    @ManyToOne
    private User assignedUser;

    @ManyToOne
    private Team assignedTeam;
}
