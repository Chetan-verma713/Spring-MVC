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
        try {
            List<Todo> todos = service.getAllTodos();
            return new ResponseEntity(todos.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addTodo(@RequestBody Todo todo) {
        try {
            service.addTodo(todo);
            return new ResponseEntity("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("failed", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @DeleteMapping(value = "/{user}/{desc}")
    @ResponseBody
    public ResponseEntity deleteTodo(@PathVariable String user, @PathVariable String desc) {
        try {
            service.deleteTodo(user, desc);
            return new ResponseEntity("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{user}")
    @ResponseBody
    public ResponseEntity retrieveTodo(@PathVariable String user) {
        try {
            List<Todo> todos = service.getTodos(user);
            return new ResponseEntity(todos.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity updateTodo(@RequestBody Todo todo) {
        try {
            service.updateTodo(todo);
            return new ResponseEntity("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("failed", HttpStatus.BAD_REQUEST);
        }
    }

}
