package com.naehas.todo;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TodoService {
    public static List<Todo> todos = new ArrayList<>();
    public static int noOfTodo = 3;

    static {

        todos.add(new Todo(1, "chetan", "Learn Spring MVC", new Date(), false));
        todos.add(new Todo(2, "chetan", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "chetan", "Learn Hibernate", new Date(), false));

    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {

        Todo newTodo = new Todo(++noOfTodo, name, desc, targetDate, isDone);

        todos.add(newTodo);

    }

    public void deleteTodo(int id) {

        List<Todo> filteredList = todos.stream()
                .filter(todo -> todo.getId() == id)
                .collect(Collectors.toList());

        todos.removeAll(filteredList);

    }

    public List<Todo> retrieveTodos(String user) {

        List<Todo> filteredTodos = new ArrayList<>();

        todos.stream()
                .filter(todo -> Objects.equals(todo.getUser(), user))
                .forEach(filteredTodos::add);

        return filteredTodos;

    }

    public Todo retrieveTodo(int id) {

        List<Todo> list = todos.stream()
                .filter(todo -> todo.getId() == id)
                .collect(Collectors.toList());

        return list.get(0);

    }

    public void updateTodo(Todo todo) {

        todos.remove(todo);
        todos.add(todo);

    }

}
