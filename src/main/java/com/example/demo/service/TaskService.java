package com.example.demo.service;

import com.example.demo.entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    ResponseEntity getTask(int id);

    List<Task> addTasks(Task task);

    List<Task> updateAllTasks(List<Task> tasksForUpdate);
    ResponseEntity updateTask(int id, Task task);

    void deleteAllTasks();
    List<Task> deleteTask(int id);








}
