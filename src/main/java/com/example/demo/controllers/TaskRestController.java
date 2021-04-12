package com.example.demo.controllers;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        return taskService.getTask(id);
    }

    @PostMapping("/addTasks")
    public List<Task> addTasks(@RequestBody Task taskForAdd) {
        return taskService.addTasks(taskForAdd);
    }

    @PutMapping("/tasks")
    public List<Task> updateAllTask(@RequestBody List<Task> tasksForUpdate) {
        return taskService.updateAllTasks(tasksForUpdate);
    }
    
    @PutMapping("/tasks/{id}")
    public ResponseEntity updateTask(@PathVariable int id, @RequestBody Task taskForUpdate) {
        return taskService.updateTask(id, taskForUpdate);
    }

    @DeleteMapping("/tasks/{id}")
    public List<Task> deleteTask(@PathVariable int id) {

        return taskService.deleteTask(id);
    }

    @DeleteMapping("/tasks/")
    public String deleteAllTasks() {
        taskService.deleteAllTasks();
        return "Deleted was successful";
    }



}
