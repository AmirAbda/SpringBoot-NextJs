package com.amir.back_end.service;

import com.amir.back_end.domain.Todo;
import com.amir.back_end.repository.TodoRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo createTodo(Todo todo) {
        // Ensure new todos are not marked as completed by default
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) {
        Todo existingTodo = todoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));
        existingTodo.setTitle(todo.getTitle());
        existingTodo.setCompleted(todo.isCompleted());
        return todoRepository.save(existingTodo);
    }

    @Override
    public Todo updateTodo(Long id, String title, Boolean completed) {
        Todo existingTodo = todoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));
        if (title != null) {
            existingTodo.setTitle(title);
        }
        if (completed != null) {
            existingTodo.setCompleted(completed);
        }
        return todoRepository.save(existingTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new EntityNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));
    }
}