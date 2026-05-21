package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class TaskService {

        private final TaskRepository taskRepository;

        public TaskService(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        public List<Task> getAllTasks() {
            return taskRepository.findAll();
        }

        public List<Task> getTasksByCompleted(Boolean completed) {
            return taskRepository.findByCompleted(completed);
        }

        public Task createTask(Task task) {
            return taskRepository.save(task);
        }

        public void deleteTask(Long id) {
            taskRepository.deleteById(id);
        }

        public Task updateTask(Long id, Task updatedTask) {

            return taskRepository.findById(id)
                    .map(task -> {

                        if (updatedTask.getTitle() != null) {
                            task.setTitle(updatedTask.getTitle());
                        }

                        task.setCompleted(updatedTask.isCompleted());

                        return taskRepository.save(task);
                    })
                    .orElseThrow(() -> new RuntimeException("Task not found"));
        }
    }

