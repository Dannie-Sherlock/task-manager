package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getUpcomingTasks());
        model.addAttribute("completedTasks", taskService.getCompletedTasks());
        model.addAttribute("newTask", new Task());
        return "index";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/update")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        taskService.updateTask(id, task);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {
        taskService.completeTask(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/batch-delete")
    public String batchDeleteTasks(@RequestParam("ids") Long[] ids) {
        taskService.batchDeleteTasks(ids);
        return "redirect:/";
    }

    @GetMapping("/tasks/{id}")
    @ResponseBody
    public Task getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
}