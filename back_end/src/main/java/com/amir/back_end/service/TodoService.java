package com.amir.back_end.service;

import java.util.List;

import com.amir.back_end.domain.Todo;

public interface TodoService {

    List<Todo> getAllTodos();
    Todo createTodo(Todo todo);
    Todo updateTodo(Long id, String title, Boolean completed);
    void deleteTodo(Long id);
    Todo updateTodo(Long id, Todo todo);
    Todo getTodoById(Long id);
}
