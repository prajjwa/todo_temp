package com.example.todo.dtos;

import com.example.todo.entities.NotesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotesOutputDTO {
    private Integer taskId;
    private NotesEntity entity;
}
