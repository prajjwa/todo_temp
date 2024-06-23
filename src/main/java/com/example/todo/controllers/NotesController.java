package com.example.todo.controllers;

import com.example.todo.dtos.CreateNotesDTO;
import com.example.todo.dtos.NotesOutputDTO;
import com.example.todo.entities.NotesEntity;
import com.example.todo.services.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks/{taskId}/notes")
public class NotesController {

    private NotesService notesService;

   public NotesController(NotesService service)
    {
        this.notesService=service;
    }

    @GetMapping("")
    public ResponseEntity<List<NotesEntity>> getNotesForTaskId(@PathVariable("taskId") Integer taskId)
    {
       List<NotesEntity> notesForTask=notesService.getNotesByTaskId(taskId);

       return ResponseEntity.ok(notesForTask);
    }

    @PostMapping("")
    public ResponseEntity<NotesOutputDTO> addNotesForTask(@RequestBody CreateNotesDTO dto,@PathVariable("taskId") Integer taskId)
    {
        NotesEntity note=notesService.addNotesForTask(taskId,dto.getDescription(),dto.getTitle());
        return ResponseEntity.ok(new NotesOutputDTO(taskId,note));
    }


}
