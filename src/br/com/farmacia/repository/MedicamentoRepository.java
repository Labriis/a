package br.com.farmacia.repository;

import br.com.farmacia.model.Medicamento; // Import da classe do outro pacote

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MedicamentoRepository {
    private final Map<Integer, Medicamento> repositorio = new HashMap<>();

    public boolean cadastrar(Medicamento medicamento) {
        if (medicamento == null || medicamento.getId() == null) {
            return false;
        }
        if (repositorio.containsKey(medicamento.getId())) {
            return false;
        }
        repositorio.put(medicamento.getId(), medicamento);
        return true;
    }

    public Optional<Medicamento> buscarPorId(Integer id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(repositorio.get(id));
    }

    public List<Medicamento> listarTodos() {
        return new ArrayList<>(repositorio.values());
    }

    public boolean atualizar(Integer id, Medicamento novosDados) {
        if (id == null || !repositorio.containsKey(id) || novosDados == null) {
            return false;
        }
        Medicamento existente = repositorio.get(id);
        existente.setNome(novosDados.getNome());
        existente.setCategoria(novosDados.getCategoria());
        existente.setPreco(novosDados.getPreco());
        existente.setQuantidadeEstoque(novosDados.getQuantidadeEstoque());
        return true;
    }

    public boolean remover(Integer id) {
        if (id == null || !repositorio.containsKey(id)) {
            return false;
        }
        repositorio.remove(id);
        return true;
    }

    public boolean estaVazio() {
        return repositorio.isEmpty();
    }
}