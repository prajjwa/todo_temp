package com.example.todo.controllers;

import com.example.todo.dtos.TaskRequestDTO;
import com.example.todo.dtos.TaskResponseDTO;
import com.example.todo.entities.Task;
import com.example.todo.services.TaskService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskResponseDTO>> getTask()
    {
        var allTasks=service.getTaskList();

        return ResponseEntity.ok(allTasks);

    }

    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody TaskRequestDTO requestDTO)
    {
        service.addTask(requestDTO);

        return ResponseEntity.ok("Task added sucessfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer taskId)
    {
        var task=service.getTaskById(taskId);

        if(task==null)return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable("id") Integer taskId)
    {
        if(!service.deleteTask(taskId))
        {
            return ResponseEntity.badRequest().body("Task id "+taskId+" does not exists.");
        }

        return ResponseEntity.ok().body("Task id "+taskId+" deleted.");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable("id") Integer taskId,
                                                      @RequestBody TaskRequestDTO taskRequestDTO)
    {

        TaskResponseDTO responseDTO=service.updateTask(taskId,taskRequestDTO);

        if(responseDTO==null)return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(responseDTO);
    }

}
