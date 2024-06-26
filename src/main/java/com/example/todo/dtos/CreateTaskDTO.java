package com.example.todo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreateTaskDTO {
private String description;
private String deadline;
private String title;
}
