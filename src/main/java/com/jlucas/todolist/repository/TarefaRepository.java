package com.jlucas.todolist.repository;

import com.jlucas.todolist.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

// <Tarefa, Long> significa: <Qual a Entidade, Qual o Tipo do ID>
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // Aqui dentro já tem:
    // .save(), .findAll(), .delete(), .findById()...
    // Tudo invisível, herdado do JpaRepository.

}