package com.tejas.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.tejas.entity.Task;

@Controller
public class TaskWebSocketController {

    @MessageMapping("/taskUpdate")  // Endpoint for incoming task update requests
    @SendTo("/topic/tasks")  // Broadcast to all subscribers on /topic/tasks
    public Task updateTask(Task task) {
        return task;  // This can return the updated task entity for clients
    }
}
