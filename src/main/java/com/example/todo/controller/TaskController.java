// REST-контроллер: принимает HTTP-запросы
package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Обработка HTTP-запросов

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

//    @GetMapping
//    public List<Task> getAllTasks() {
//        return service.getAllTasks();
//    }

    @GetMapping
    public List<Task> getTasks(@RequestParam(required = false) Boolean completed) {

        if (completed == null) {
            return service.getAllTasks();
        }

        return service.getTasksByCompleted(completed);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody Task updatedTask) {

        return service.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}

//
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//
//    private final TaskRepository taskRepository;
//
//    // Конструктор — Spring сам "впрыснет" TaskRepository
//    public TaskController(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }
//
//    // Получить все задачи
//    @GetMapping
//    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }
//
//    // Добавить задачу
//    @PostMapping
//    public Task createTask(@RequestBody Task task) {
//        return taskRepository.save(task);
//    }
//    // Обновить задачу (по ID)
//    @PutMapping("/{id}")
//    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
//        return taskRepository.findById(id)
//                .map(task -> {
//                    task.setTitle(updatedTask.getTitle());
//                    task.setCompleted(updatedTask.isCompleted());
//                    return taskRepository.save(task);
//                })
//                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
//    }
//
//    // Удалить задачу (по ID)
//    @DeleteMapping("/{id}")
//    public void deleteTask(@PathVariable Long id) {
//        taskRepository.deleteById(id);
//    }
//}
