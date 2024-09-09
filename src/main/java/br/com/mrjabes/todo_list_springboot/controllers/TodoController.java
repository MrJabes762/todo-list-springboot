package br.com.mrjabes.todo_list_springboot.controllers;

import br.com.mrjabes.todo_list_springboot.model.todo.Todo;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mrjabes.todo_list_springboot.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;


    public TodoController(TodoService todoService) {
       setTodoService(todoService);
    }

    public List<Todo> create(Todo todo){
        return getTodoService().create(todo);
    }
    public List<Todo> list (){
        return getTodoService().list();
    }
    public List<Todo> update (Todo todo){
        return getTodoService().update(todo);
    }
    public List<Todo> delete (Long id){
        return getTodoService().delete(id);
    }

    public TodoService getTodoService() {
        return this.todoService;
    }

    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

}
