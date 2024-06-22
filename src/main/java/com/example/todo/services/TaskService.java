package com.example.todo.services;

import com.example.todo.entities.Task;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
@Data
public class TaskService {
    private final ArrayList<Task> taskList=new ArrayList<>();

    private int id=1;

    private SimpleDateFormat deadlineFormatter=new SimpleDateFormat("yyyy-mm-dd");

    public Task addTask(String description, String title, String deadline) throws ParseException {
        Task task=new Task();
        task.setId(id);
        task.setCompleted(false);
        task.setDescription(description);
        task.setDeadline((Date) deadlineFormatter.parse(deadline));
        task.setTitle(title);

        taskList.add(task);

        id++;

        return task;

    }


   public Task getTaskById(Integer id){
        for(Task task:taskList)
        {
            if(id==task.getId())return task;
        }

        return null;
   }

   public Task editTask(Integer id,String description,String deadline,boolean completed) throws ParseException {
       Task task=getTaskById(id);

       if(task==null)return null;

       task.setDeadline(deadlineFormatter.parse(deadline));
       task.setCompleted(completed);
       task.setDescription(description);
       return task;
   }
}
