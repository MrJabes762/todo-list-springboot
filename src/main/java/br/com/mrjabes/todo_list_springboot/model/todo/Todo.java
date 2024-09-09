package br.com.mrjabes.todo_list_springboot.model.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity// Entidade do Banco de Dados
@Table(name = "Todos")//Nome Da Tabela 
public class Todo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;// Id do registro
    private String nome;
    private String descricao;
    private boolean realizado;
    private Prioridade prioridade;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isRealizado() {
        return this.realizado;
    }

    public boolean getRealizado() {
        return this.realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public Prioridade getPrioridade() {
        return this.prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

}
