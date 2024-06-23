package com.example.todo.services;

import com.example.todo.entities.NotesEntity;
import com.example.todo.entities.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotesService {
   private TaskService taskService;

   public NotesService(TaskService service)
   {
       this.taskService=service;
   }

   private Map<Integer,NotesHolder> taskToNotesMap=new HashMap<>();

   class NotesHolder{
       protected int id=1;
       protected ArrayList<NotesEntity> arr=new ArrayList<>();
   }

   public ArrayList<NotesEntity> getNotesByTaskId(int taskId){
       Task task=taskService.getTaskById(taskId);

       if(task==null)return  null;

       if(taskToNotesMap.get(taskId)==null)
            taskToNotesMap.put(taskId,new NotesHolder());

       return taskToNotesMap.get(taskId).arr;
   }

   public NotesEntity addNotesForTask(int taskId,String description,String title)
   {
       Task task=taskService.getTaskById(taskId);

       if(task==null)
           return null;

       if(taskToNotesMap.get(taskId)==null)taskToNotesMap.put(taskId,new NotesHolder());

       if(taskToNotesMap.get(taskId)==null)
            taskToNotesMap.put(taskId,new NotesHolder());

       NotesHolder holder=taskToNotesMap.get(taskId);

       NotesEntity notes=NotesEntity.builder().build();

       notes.setNotesId(holder.id);
       notes.setTitle(title);

       notes.setDescription(description);

       holder.arr.add(notes);
       holder.id++;

       return notes;

   }



}
