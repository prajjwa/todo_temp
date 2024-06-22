package com.example.todo.controllers;

import com.example.todo.dtos.CreateTaskDTO;
import com.example.todo.dtos.EditDTO;
import com.example.todo.dtos.ErrorDTO;
import com.example.todo.entities.Task;
import com.example.todo.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id)
    {
        var task =service.getTaskById(id);

        if(task==null)return ResponseEntity.notFound().build();

        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> editTask(@PathVariable("id") Integer id, @RequestBody EditDTO editDTO) throws ParseException {
        Task task=service.editTask(id,editDTO.getDescription(),editDTO.getDeadline(),editDTO.getCompleted());
        if(task==null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(task);
    }

    @PostMapping("/")
    public ResponseEntity<Task> addTask(@RequestBody CreateTaskDTO dto) throws ParseException {
        Task addedTask=service.addTask(dto.getDescription(),dto.getTitle(),dto.getDeadline());

        return  ResponseEntity.ok(addedTask);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e)
    {
        ErrorDTO errorDTO=new ErrorDTO();
        if(e instanceof ParseException)
        {
            errorDTO.setMessage("Invalid Format");
        }
        else
          errorDTO.setMessage(e.getMessage());

        return ResponseEntity.badRequest().body(errorDTO);
    }

}
