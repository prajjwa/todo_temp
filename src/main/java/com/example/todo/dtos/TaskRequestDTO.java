package com.example.todo.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskRequestDTO {

    private Date deadline;

    private String description;

    private String title;

    private Boolean isCompleted;
}