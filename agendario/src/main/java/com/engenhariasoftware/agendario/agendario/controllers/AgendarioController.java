package com.engenhariasoftware.agendario.agendario.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engenhariasoftware.agendario.agendario.model.Agendario;
import com.engenhariasoftware.agendario.agendario.service.AgendarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendas")
public class AgendarioController {
     
    private AgendarioService agendarioService;


    public AgendarioController(AgendarioService agendarioService) {
       setAgendarioService(agendarioService);
    }

    @PostMapping
    public List<Agendario> create(@RequestBody @Valid Agendario todo){// vai pegar a tarefa e criar 
        return getAgendarioService().create(todo);
    }

    @GetMapping
    public List<Agendario> list (){
        return getAgendarioService().list();
    }

    @PutMapping
    public List<Agendario> update (@RequestBody Agendario todo){// vai pegar a tarefa e fazer o update 
        return getAgendarioService().update(todo);
    }

    @DeleteMapping("{id}")// o valor vai ser recuperado da requisição 
    public List<Agendario> delete (@PathVariable("id") Long id){// vai receber o Id do objato a ser deletado 
        return getAgendarioService().delete(id);// enviar pra camada de serviço para deletar
    }

    public AgendarioService getAgendarioService() {
        return this.agendarioService;
    }

    public void setAgendarioService(AgendarioService agendarioService) {
        this.agendarioService = agendarioService;
    }

   
}
