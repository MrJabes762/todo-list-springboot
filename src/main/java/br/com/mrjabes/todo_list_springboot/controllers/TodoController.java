package br.com.mrjabes.todo_list_springboot.controllers;

import br.com.mrjabes.todo_list_springboot.model.todo.Todo;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mrjabes.todo_list_springboot.service.TodoService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;


    public TodoController(TodoService todoService) {
       setTodoService(todoService);
    }

    @PostMapping
    public List<Todo> create(@RequestBody @Valid Todo todo){// vai pegar a tarefa e criar 
        return getTodoService().create(todo);
    }

    @GetMapping
    public List<Todo> list (){
        return getTodoService().list();
    }

    @PutMapping
    public List<Todo> update (@RequestBody Todo todo){// vai pegar a tarefa e fazer o update 
        return getTodoService().update(todo);
    }

    @DeleteMapping("{id}")// o valor vai ser recuperado da requisição 
    public List<Todo> delete (@PathVariable("id") Long id){// vai receber o Id do objato a ser deletado 
        return getTodoService().delete(id);// enviar pra camada de serviço para deletar
    }

    public TodoService getTodoService() {
        return this.todoService;
    }

    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

}
