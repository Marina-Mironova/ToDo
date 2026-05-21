// Сущность (Entity): описывает задачу
package com.example.todo.model;
//Описание сущности задачи

import jakarta.persistence.*;

// Аннотация JPA — говорит, что это таблица в базе
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // автоинкремент
    private Long id;

    private String title;

    private boolean completed;

    // Конструктор без аргументов обязателен для JPA
    public Task() {}

    // Дополнительный конструктор для удобства
    public Task(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}




