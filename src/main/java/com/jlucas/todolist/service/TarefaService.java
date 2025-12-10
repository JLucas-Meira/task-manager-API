package com.jlucas.todolist.service;
import com.jlucas.todolist.entity.Tarefa;
import com.jlucas.todolist.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    // INJEÇÃO DE DEPENDENCIA
    // O Service precisa do Repository para trabalhar.
    private final TarefaRepository repository;

    // Construtor: O Spring vai "entregar" o repository pronto aqui quando iniciar.
    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    // --- MÉTODOS DE AÇÃO (O Chef Cozinhando) ---

    // CRIAR TAREFA
    public Tarefa criar(Tarefa tarefa) {
        // (No futuro, validações entraram aqui. Ex: "Título não pode ser vazio")
        return repository.save(tarefa);
    }

    // LISTAR TAREFA
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    // ATUALIZAR TAREFA(Com regra de negócio)
    public Tarefa atualizar(Long id, Tarefa tarefa) {
        // Regra: Só atualizo se existir
        if (repository.existsById(id)) {
            tarefa.setId(id); // Garante a consistência
            return repository.save(tarefa);
        }
        return null; // Retorna nulo se não achar (O Controller vai decidir o que fazer com isso)
    }

    // DELETAR
    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true; // Deletou com sucesso
        }
        return false; // Não achou para deletar
    }
}