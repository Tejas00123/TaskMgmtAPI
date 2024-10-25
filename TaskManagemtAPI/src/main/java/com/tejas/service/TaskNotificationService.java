package com.tejas.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tejas.entity.Task;
import com.tejas.repository.TaskRepository;

@Service
public class TaskNotificationService {

    @Autowired
    private TaskRepository taskRepository; // Assuming you have a TaskRepository

    @Autowired
    private JavaMailSender mailSender; // Inject the JavaMailSender

    // Check for overdue tasks every hour
    @Scheduled(fixedRate = 3600000) // 1 hour in milliseconds
    public void checkForOverdueTasks() {
        Date now = new Date();
        List<Task> overdueTasks = taskRepository.findByDueDateBeforeAndCompletedFalse(now);
        List<Task> upcomingTasks = taskRepository.findByDueDateBetweenAndCompletedFalse(now, new Date(now.getTime() + 24 * 3600 * 1000)); // Next 24 hours

        // Notify users for overdue tasks
        for (Task task : overdueTasks) {
            sendEmail("tejasgavali516@gmail.com", "Overdue Task Notification", "Task overdue: " + task.getTitle());
        }

        // Notify users for upcoming tasks
        for (Task task : upcomingTasks) {
            sendEmail("tejasgavali516@gmail.com", "Upcoming Task Notification", "Task due soon: " + task.getTitle());
        }
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
