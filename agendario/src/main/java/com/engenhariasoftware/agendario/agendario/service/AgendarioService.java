package com.engenhariasoftware.agendario.agendario.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.engenhariasoftware.agendario.agendario.data.AgendarioRepository;
import com.engenhariasoftware.agendario.agendario.model.Agendario;

@Service
public class AgendarioService {
    
    private AgendarioRepository agendarioRepository;

    public AgendarioService (AgendarioRepository agendario){
        setAgendarioRepository(agendario);
    }

    
    public List<Agendario> create(Agendario tarefa){// Criar o Usuario
        getAgendarioRepository().save(tarefa);
        return list();
    }

    
    public List<Agendario> list(){// Pegar a Lista
        Sort sort = Sort.by("prioridade")// Primeiro Ordenta Pela Tarefa de Maior Prioridade 
        .descending()
        .and(Sort.by("nome").ascending());// Depois Ordena pelo nome por ordem alfabetica
        return getAgendarioRepository().findAll(sort);
    }
    
    public List<Agendario> update(Agendario todo){// Atualizar o usuario 
        getAgendarioRepository().save(todo);
        return list();
    }

    public List<Agendario> delete(Long id){//Deletar o Todo 
        getAgendarioRepository().deleteById(id);
        return list();
    }

    public AgendarioRepository getAgendarioRepository() {
        return this.agendarioRepository;
    }

    public void setAgendarioRepository(AgendarioRepository agendarioRepository) {
        this.agendarioRepository = agendarioRepository;
    }

}
