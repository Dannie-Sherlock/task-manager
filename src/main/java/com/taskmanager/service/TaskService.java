package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByEndTimeAsc();
    }

    public List<Task> getUpcomingTasks() {
        return taskRepository.findUpcomingTasks(LocalDateTime.now());
    }

    public List<Task> getCompletedTasks() {
        return taskRepository.findCompletedTasks(LocalDateTime.now());
    }

    public Task createTask(Task task) {
        if (task.getProgress() == null) {
            task.setProgress(0);
        }
        if (task.getIsUrgent() == null) {
            task.setIsUrgent(false);
        }
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStartTime(taskDetails.getStartTime());
        task.setEndTime(taskDetails.getEndTime());
        task.setProgress(taskDetails.getProgress());
        task.setIsUrgent(taskDetails.getIsUrgent());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void completeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setProgress(100);
        task.setEndTime(LocalDateTime.now());
        taskRepository.save(task);
    }

    public void batchDeleteTasks(Long[] ids) {
        for (Long id : ids) {
            taskRepository.deleteById(id);
        }
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}