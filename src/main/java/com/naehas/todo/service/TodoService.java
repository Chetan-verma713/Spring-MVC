package com.naehas.todo.service;

import com.naehas.todo.dao.TodoDao;
import com.naehas.todo.model.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TodoService {
    @Autowired
    private TodoDao todoDao;

    public void setTodoDao(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Transactional
    public void addTodo(Todo todo) {
        todoDao.addTodo(todo);
    }

    @Transactional
    public void deleteTodo(String name, String desc) {
        todoDao.deleteTodo(name, desc);
    }


    @Transactional
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    @Transactional
    public List<Todo> getTodos(String user) {
        return todoDao.getTodos(user);
    }

    @Transactional
    public Todo getTodo(String user, String desc) {
        return todoDao.getTodo(user, desc);
    }

    @Transactional
    public void updateTodo(Todo todo) {

        todoDao.updateTodo(todo);

    }

}
