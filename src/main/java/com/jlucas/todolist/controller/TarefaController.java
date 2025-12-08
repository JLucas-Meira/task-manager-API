package com.jlucas.todolist.controller;

import com.jlucas.todolist.entity.Tarefa;
import com.jlucas.todolist.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas") // 1. Define que tudo aqui começa com localhost:8080/tarefas
public class TarefaController {

    // 2. INJEÇÃO DE DEPENDÊNCIA
    // "Spring, me dá aquele Repository pronto que você criou."
    @Autowired
    private TarefaRepository repository;

    // 3. CRIAR (POST)
    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        // O @RequestBody pega o JSON que mandar no Postman e transforma em um objeto Java sozinho!
        return repository.save(tarefa); // O .save() faz o INSERT no banco e retorna o item salvo.
    }

    // 4. LISTAR TUDO (GET)
    @GetMapping
    public List<Tarefa> listar() {
        return repository.findAll(); // O .findAll() faz o SELECT * FROM tb_tarefas.
    }

    // 5. ATUALIZAR (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        // Verifica se existe antes de tentar atualizar
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna erro 404 se não achar
        }

        tarefa.setId(id); // Garante que o ID do objeto é o mesmo da URL
        Tarefa tarefaAtualizada = repository.save(tarefa);
        return ResponseEntity.ok(tarefaAtualizada); // Retorna 200 OK
    }

    // 6. DELETAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Verifica se existe
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        // Retorna 204 No Content (Padrão mundial para deleção com sucesso)
        return ResponseEntity.noContent().build();
    }
}