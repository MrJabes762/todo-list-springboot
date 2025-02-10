package com.engenhariasoftware.agendario.agendario.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engenhariasoftware.agendario.agendario.model.Agendario;

public interface AgendarioRepository extends JpaRepository<Agendario, Long> {
    
}
