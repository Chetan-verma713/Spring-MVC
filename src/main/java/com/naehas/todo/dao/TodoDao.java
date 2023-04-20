package com.naehas.todo.dao;

import com.naehas.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class TodoDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void addTodo(Todo todo) {
        try {
            hibernateTemplate.save(todo);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Already Exist");
        }
    }

    public void deleteTodo(String user, String desc) {

        Todo todo = hibernateTemplate.load(Todo.class, searchByUserDesc(user, desc));

        hibernateTemplate.delete(todo);

    }

    @SuppressWarnings("unchecked")
    public List<Todo> getAllTodos() {

        List<Todo> todos = hibernateTemplate.loadAll(Todo.class);

        return todos.isEmpty() ? new ArrayList<>() : todos;

    }

    @SuppressWarnings("unchecked")
    public List<Todo> getTodos(String user) {

        List<Todo> todos = searchByUser(user);

        return todos;
    }

    public Todo getTodo(String user, String desc) {

        return hibernateTemplate.load(Todo.class, searchByUserDesc(user, desc));

    }

    public void updateTodo(Todo newTodo) {

        Todo originalTodo = getTodo(newTodo.getUser(), newTodo.getDesc());

        if (newTodo.getTargetDate() == null) {
            newTodo.setTargetDate(originalTodo.getTargetDate());
        } else if (originalTodo.getTargetDate() != newTodo.getTargetDate()) {
            originalTodo.setTargetDate(newTodo.getTargetDate());
        }

        if (originalTodo.getDone() != newTodo.getDone()) {
            originalTodo.setDone(newTodo.getDone());
        }

        hibernateTemplate.update(originalTodo);

    }


    private List<Todo> searchByUser(String user) {

        return getAllTodos().stream()
                .filter(todo -> Objects.equals(todo.getUser(), user))
                .collect(Collectors.toList());

    }

    private int searchByUserDesc(String user, String desc) {

        Todo userDescTodo = getAllTodos().stream()
                .filter(todo -> Objects.equals(todo.getUser(), user) && Objects.equals(desc, todo.getDesc()))
                .collect(Collectors.toList()).get(0);

        return userDescTodo.getId();

    }

}