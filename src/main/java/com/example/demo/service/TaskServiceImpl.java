package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.model.dao.TaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl  implements TaskService {
    @Autowired
    TaskDAO taskDAO;

    @Override
    public List<Task> getAllTasks() {
        Iterable<Task> tasksIterable = taskDAO.findAll();
        List<Task> tasks = new ArrayList<>();
        tasksIterable.forEach(tasks::add);
        return tasks;
    }

    @Override
    public ResponseEntity getTask(int id) {
        Optional<Task> optionalTask = taskDAO.findById(id);
        if (optionalTask.isPresent()) {
            return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
    }

    @Override
    public List<Task> addTasks(Task taskForAdd) {
        taskDAO.save(taskForAdd);
        Iterable<Task> tasksIterable = taskDAO.findAll();
        List<Task> tasks = new ArrayList<>();
        tasksIterable.forEach(tasks::add);
        return tasks;
    }

    @Override
    public List<Task> updateAllTasks(List<Task> tasksForUpdate) {
        Iterable<Task> taskIterable = taskDAO.findAll();
        List<Task> taskForPrint = new ArrayList<>();
        for(Task taskForUpdate : tasksForUpdate) {
            for (Task curTask : taskIterable) {
                if (taskForUpdate.getId() == curTask.getId()) {
                    curTask.setName(taskForUpdate.getName());
                    curTask.setTask(taskForUpdate.getTask());
                    taskDAO.save(curTask);
                    taskForPrint.add(curTask);
                }
            }
        }
        return taskForPrint;
    }

    @Override
    public ResponseEntity updateTask(int id, Task taskForUpdate) {
        Optional<Task> optionalTask = taskDAO.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task getTask = optionalTask.get();
        getTask.setName(taskForUpdate.getName());
        getTask.setTask(taskForUpdate.getTask());
        taskDAO.save(getTask);

        Iterable<Task> tasksIterable = taskDAO.findAll();
        List<Task> tasks = new ArrayList<>();
        for (Task task : tasksIterable) {
            tasks.add(task);
        }
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @Override
    public void deleteAllTasks() {
        taskDAO.deleteAll();
    }

    @Override
    public List<Task> deleteTask(int id) {
        taskDAO.deleteById(id);
        Iterable<Task> tasksIterable = taskDAO.findAll();
        List<Task> tasks = new ArrayList<>();
        tasksIterable.forEach(tasks::add);
        return tasks;
    }
}
