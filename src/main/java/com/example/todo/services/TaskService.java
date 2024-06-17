package com.example.todo.services;

import com.example.todo.dtos.TaskRequestDTO;
import com.example.todo.dtos.TaskResponseDTO;
import com.example.todo.entities.Task;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class TaskService {

    @Autowired
    TaskRepository repository;

    // insert
    public void addTask(TaskRequestDTO requestDTO)
    {
        Task taskToBeSaved=Task.builder()
                .deadline(requestDTO.getDeadline())
                .isCompleted(requestDTO.getIsCompleted())
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .build();

        repository.save(taskToBeSaved);
    }

   public TaskResponseDTO getTaskById(Integer id){

        Optional<Task> optionalTask=repository.findById(id);

        if(optionalTask.isEmpty())return null;

        Task queriedTask=optionalTask.get();

       return TaskResponseDTO.builder()
               .deadline(queriedTask.getDeadline())
               .description(queriedTask.getDescription())
               .isCompleted(queriedTask.getIsCompleted())
               .title(queriedTask.getTitle())
               .build();
   }

    public List<TaskResponseDTO> getTaskList() {

        List<TaskResponseDTO> allTasks=new ArrayList<>();

        List<Task> tasksInDb=repository.findAll();

        for(Task task:tasksInDb)
        {
            TaskResponseDTO responseDTO=TaskResponseDTO.builder()
                    .deadline(task.getDeadline())
                    .description(task.getDescription())
                    .isCompleted(task.getIsCompleted())
                    .title(task.getTitle())
                    .build();

            allTasks.add(responseDTO);
        }

        return allTasks;
    }

    public Boolean deleteTask(Integer taskId)
    {
        if(repository.existsById(taskId)==false)return false;

        repository.deleteById(taskId);

        return  true;
    }

    public TaskResponseDTO updateTask(Integer taskId,TaskRequestDTO requestDTO)
    {
        Optional<Task> optionalTask=repository.findById(taskId);

        if(optionalTask.isEmpty())return null;

        Task task=optionalTask.get();

        if(requestDTO.getIsCompleted()!=null)
        {
            task.setIsCompleted(requestDTO.getIsCompleted());
        }

        if(requestDTO.getDeadline()!=null)
        {
            task.setDeadline(requestDTO.getDeadline());
        }

        if(requestDTO.getDescription()!=null)
        {
           task.setDescription(requestDTO.getDescription());
        }

        if(requestDTO.getTitle()!=null)
        {
           task.setTitle(requestDTO.getTitle());
        }

       repository.save(task);

       return this.getTaskById(taskId);
    }
}
