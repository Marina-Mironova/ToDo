// Интерфейс для работы с БД
package com.example.todo.repository;
//Интерфейс доступа к базе

import com.example.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Интерфейс-репозиторий для работы с таблицей Task
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompletedFalse();      // Например, получить все незавершённые задачи
    List<Task> findByCompleted(Boolean completed);
}
