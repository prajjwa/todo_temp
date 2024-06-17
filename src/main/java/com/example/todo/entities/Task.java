package com.example.todo.entities;

import lombok.Data;

import java.util.Date;
@Data
public class Task {
    private int id;
    private Date deadline;

    private String description;

    private String title;

    private boolean completed;
}
