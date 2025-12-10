package com.jlucas.todolist.entity;

import jakarta.persistence.*;

@Entity // Diz para o Spring: "Isso aqui vira uma tabela no banco"
@Table(name = "tb_tarefas")
public class Tarefa {

    @Id // Este atributo é a Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz pro banco gerar o ID automático (1, 2, 3...)
    private Long id;

    private String titulo;
    private String descricao;
    private boolean finalizado;

    // --- GETTERS E SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}