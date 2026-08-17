package com.example.todo.service;

import com.example.todo.model.Task;
import com.example.todo.model.TaskStatus;
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

            if (task.getStatus() == null) {

                if (task.isCompleted()) {
                    task.setStatus(TaskStatus.DONE);
                } else {
                    task.setStatus(TaskStatus.TODO);
                }

            }



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

                        // =========================
                        // Защита от null
                        // =========================

                        if (updatedTask.getStatus() != null) {

                            task.setStatus(updatedTask.getStatus());

                        } else {

                            // старый frontend

                            if (task.isCompleted()) {
                                task.setStatus(TaskStatus.DONE);
                            } else {
                                task.setStatus(TaskStatus.TODO);
                            }

                        }




                        return taskRepository.save(task);
                    })
                    .orElseThrow(() -> new RuntimeException("Task not found"));
        }

        public Task getTaskById(Long id) {

            return taskRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Task not found"));

        }

        private void updateStatus(Task task) {

            // =========================
            // Синхронизация status
            // =========================

            if (task.isCompleted()) {
                task.setStatus(TaskStatus.DONE);
            } else {
                task.setStatus(TaskStatus.TODO);
            }


        }


    }

