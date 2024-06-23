package com.example.todo.dtos;

import com.example.todo.entities.NotesEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class TaskResponseDTO {
    private int id;
    private Date deadline;

    private String description;

    private String title;

    private boolean completed;

    ArrayList<NotesEntity> notesEntities;
}
