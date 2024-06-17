package com.example.todo.services;

import com.example.todo.entities.Task;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;

@Service
@Data
public class TaskService {
    private final ArrayList<Task> taskList=new ArrayList<>();

    private int id=1;

    public void addTask(String description, String title, String deadline)
    {
        Task task=new Task();
        task.setId(id);
        task.setCompleted(false);
        task.setDescription(description);
        task.setDeadline(new Date(deadline));
        task.setTitle(title);

        taskList.add(task);

        id++;

    }


   public Task getTaskById(int id){
        for(Task task:taskList)
        {
            if(id==task.getId())return task;
        }

        return null;
   }
}
