package com.naehas.todo.controller;

import com.naehas.todo.model.Todo;
import com.naehas.todo.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService service;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity showTodos() {

        System.out.println("list-todo triggered.");

        List<Todo> todos = service.getAllTodos();

        return new ResponseEntity(todos.toString(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addTodo(@RequestBody Todo todo) {

        System.out.println("add-todo triggered.");

        if (todo.getDesc().length() <= 6) {
            return new ResponseEntity("failed", HttpStatus.BAD_REQUEST);
        }

        service.addTodo(todo);

        return new ResponseEntity("success", HttpStatus.OK);

    }

    @DeleteMapping(value = "/{user}/{desc}")
    @ResponseBody
    public ResponseEntity deleteTodo(@PathVariable String user, @PathVariable String desc) {

        System.out.println("delete-todo triggered.");

        service.deleteTodo(user, desc);

        return new ResponseEntity("success", HttpStatus.OK);

    }

    @GetMapping(value = "/{user}")
    @ResponseBody
    public ResponseEntity retrieveTodo(@PathVariable String user) {

        System.out.println("retrieve-todo triggered.");

        List<Todo> todos = service.getTodos(user);

        return new ResponseEntity(todos.toString(), HttpStatus.OK);

    }

    @PutMapping
    @ResponseBody
    public ResponseEntity updateTodo(@RequestBody Todo todo) {

        System.out.println("update-todo triggered.");

        service.updateTodo(todo);

        return new ResponseEntity("success", HttpStatus.OK);

    }

}
