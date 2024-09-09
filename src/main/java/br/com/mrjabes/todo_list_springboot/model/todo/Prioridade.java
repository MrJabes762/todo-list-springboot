package br.com.mrjabes.todo_list_springboot.model.todo;

public enum Prioridade {
    Alta(1),
    Media(2),
    Baixa(3),
    Planejada(4);

    private final int valor;

    Prioridade (int valor){
        this.valor = valor;
    }
    public int getValor (){
        return valor;
    }
}
