package br.com.mrjabes.todo_list_springboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.mrjabes.todo_list_springboot.data.TodoRepository;
import br.com.mrjabes.todo_list_springboot.model.todo.Todo;

@Service
public class TodoService {
    private TodoRepository todoRepository;


    public TodoService(TodoRepository todoRepository) {
       setTodoRepository(todoRepository);
    }
    
    public List<Todo> create(Todo tarefa){// Criar o Usuario
        getTodoRepository().save(tarefa);
        return list();
    }
    public List<Todo> list(){// Pegar a Lista
        Sort sort = Sort.by("prioridade")// Primeiro Ordenta Pela Tarefa de Maior Prioridade 
        .descending()
        .and(Sort.by("nome").ascending());// Depois Ordena pelo nome por ordem alfabetica
        return getTodoRepository().findAll(sort);
    }
    public List<Todo> update(Todo todo){// Atualizar o usuario 
        getTodoRepository().save(todo);
        return list();
    }
    public List<Todo> delete(Long id){//Deletar o Todo 
        getTodoRepository().deleteById(id);
        return list();
    }
    public TodoRepository getTodoRepository() {
        return this.todoRepository;
    }

    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

}
