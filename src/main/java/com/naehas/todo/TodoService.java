package com.naehas.todo;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TodoService {
    public static List<Todo> todoList = new ArrayList<>();
    public static int countTodo = 3;

    static {

        todoList.add(new Todo(1, "chetan", "Learn Spring MVC", new Date(), false));
        todoList.add(new Todo(2, "chetan", "Learn Struts", new Date(), false));
        todoList.add(new Todo(3, "chetan", "Learn Hibernate", new Date(), false));

    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {

        Todo newTodo = new Todo(++countTodo, name, desc, targetDate, isDone);

        todoList.add(newTodo);

    }

    public void deleteTodo(int id) {

        Iterator<Todo> iterator = todoList.iterator();

        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }

//        todoList.stream()
//                .filter(todo -> todo.getId() == id)
//                .forEach(todoList::remove);

    }

    public List<Todo> retrieveTodos(String user) {

        List<Todo> filteredTodos = new ArrayList<>();

        todoList.stream()
                .filter(todo -> Objects.equals(todo.getUser(), user))
                .forEach(filteredTodos::add);

        return filteredTodos;

    }

    public Todo retrieveTodo(int id) {

        List<Todo> list = todoList.stream()
                .filter(todo -> todo.getId() == id)
                .collect(Collectors.toList());

        return list.get(0);

    }

    public void updateTodo(Todo todo) {

        todoList.remove(todo);
        todoList.add(todo);

    }


}
