package com.jlucas.todolist.controller;

import com.jlucas.todolist.entity.Tarefa;
import com.jlucas.todolist.repository.TarefaRepository;
import com.jlucas.todolist.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas") //Define que tudo aqui começa com localhost:8080/tarefas
public class TarefaController {

    // INJEÇÃO DE DEPENDÊNCIA
    // "Spring, me dá aquele Repository pronto que você criou."
    @Autowired
    private final TarefaService service; // Agora chamamos o Service

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    // CRIAR (POST)
    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        // O @RequestBody pega o JSON que mandar no Postman e transforma em um objeto Java sozinho!
        return service.criar(tarefa); // O .save() faz o INSERT no banco e retorna o item salvo.
    }

    // LISTAR TUDO (GET)
    @GetMapping
    public List<Tarefa> listar() {
        return service.listarTodas(); // O .findAll() faz o SELECT * FROM tb_tarefas.
    }

    // ATUALIZAR (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        // Chama o Chef
        Tarefa tarefaAtualizada = service.atualizar(id, tarefa);

        // Se o Chef devolveu nulo, é porque não achou (404)
        if (tarefaAtualizada == null) {
            return ResponseEntity.notFound().build();
        }

        // Se devolveu a tarefa, entrega com sucesso (200)
        return ResponseEntity.ok(tarefaAtualizada);
    }


    // DELETAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Chama o Chef e pergunta: "Deu certo?"
        boolean deletou = service.deletar(id);

        if (deletou) {
            return ResponseEntity.noContent().build(); // 204 Sucesso
        }

        return ResponseEntity.notFound().build(); // 404 Não achou
    }
}