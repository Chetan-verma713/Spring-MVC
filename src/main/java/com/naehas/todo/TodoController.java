package com.naehas.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService service;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format,false));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String showTodos() {

        System.out.println("list-todo triggered.");

        service.retrieveTodos("chetan");

        return TodoService.todoList.toString();

    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String addTodo(@RequestParam String user, @RequestParam String desc, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date targetDate, @RequestParam(defaultValue = "false") boolean isDone) {

        System.out.println("add-todo triggered.");

        if (desc.length() <= 6) {
            System.out.println("failed to add todo.");
            return "failed";
        }

        service.addTodo(user, desc, targetDate, isDone);

        return "success";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTodo(@PathVariable int id) {

        System.out.println("delete-todo triggered.");

        service.deleteTodo(id);

        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String retrieveTodo(@PathVariable int id) {

        System.out.println("retrieve-todo triggered.");

        Todo todo = service.retrieveTodo(id);

        return todo.toString();

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateTodo(@PathVariable int id, @RequestParam(required = false) String user, @RequestParam(required = false) String desc, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date targetDate, @RequestParam(required = false, defaultValue = "false") boolean isDone) {

        System.out.println("update-todo triggered.");

        if (desc != null && desc.length() <= 6) {
            System.out.println("failed to add todo.");
            return "failed";
        }

        Todo todo = service.retrieveTodo(id);

        Todo targetTodo = new Todo(
                id,
                user == null ? todo.getUser() : user,
                desc == null ? todo.getDesc() : desc,
                targetDate == null ? todo.getTargetDate() : targetDate,
                isDone || todo.isDone()
        );

        service.updateTodo(targetTodo);

        return "success";

    }

}
