package com.example.todo.dtos;

import lombok.Data;

@Data
public class EditDTO {
    private String deadline;
    private String description;
    private Boolean completed;
}
