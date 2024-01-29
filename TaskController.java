package com.testkube.taskmanagement.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final List<String> tasks = new ArrayList<>();

    @PostMapping
    public String createTask(@RequestBody String task) {
        tasks.add(task);
        return "Task created successfully";
    }

    @GetMapping
    public List<String> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable int id) {
        if (id >= 0 && id < tasks.size()) {
            return tasks.get(id);
        } else {
            return "Task not found";
        }
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable int id, @RequestBody String updatedTask) {
        if (id >= 0 && id < tasks.size()) {
            tasks.set(id, updatedTask);
            return "Task updated successfully";
        } else {
            return "Task not found";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        if (id >= 0 && id < tasks.size()) {
            tasks.remove(id);
            return "Task deleted successfully";
        } else {
            return "Task not found";
        }
    }
}
