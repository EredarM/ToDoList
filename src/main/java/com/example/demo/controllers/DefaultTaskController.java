package com.example.demo.controllers;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DefaultTaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/getTaskInfo")
    public String getTaskInfo(Model model, @RequestParam() String id) {
        ResponseEntity task = taskService.getTask(Integer.parseInt(id));
        model.addAttribute("task", task.getBody());
        return "task-info";
    }

    @GetMapping("/addNewTaskPage")
    public String addNewTask() {
        return "add-new-task";
    }

    @GetMapping("/updateTaskPage")
    public String updateTaskPage(Model model, @RequestParam() String id) {
        ResponseEntity task = taskService.getTask(Integer.parseInt(id));
        model.addAttribute("task", task.getBody());
        return "update-current-task";
    }

    @PostMapping("/updateTask")
    public String updateTask(@RequestParam() String id, @RequestParam String name, @RequestParam String target) {
        Task task = new Task();
        task.setName(name);
        task.setTask(target);
        taskService.updateTask(Integer.parseInt(id), task);
        return "redirect:/";
    }

    @PostMapping("/saveTask")
    public String saveNewTask(@RequestParam String name, @RequestParam String target) {
        Task task = new Task();
        task.setName(name);
        task.setTask(target);
        taskService.addTasks(task);
        return "redirect:/";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam() String id) {
        taskService.deleteTask(Integer.parseInt(id));
        return "redirect:/";
    }
}
