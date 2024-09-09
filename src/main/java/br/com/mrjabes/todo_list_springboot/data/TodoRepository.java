package br.com.mrjabes.todo_list_springboot.data;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mrjabes.todo_list_springboot.model.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>{
    
}
