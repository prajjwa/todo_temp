package com.example.todo.controllers;

import com.example.todo.entities.Task;
import com.example.todo.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Task>> getTask()
    {
        var allTasks=service.getTaskList();

        return ResponseEntity.ok(allTasks);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(int id)
    {
        var task =service.getTaskById(id);

        if(task==null)return ResponseEntity.notFound().build();

        return ResponseEntity.ok(task);
    }
}
