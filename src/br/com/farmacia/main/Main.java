package br.com.farmacia.main;

import br.com.farmacia.model.Medicamento; // Import do modelo
import br.com.farmacia.repository.MedicamentoRepository; // Import do repositório

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final MedicamentoRepository repository = new MedicamentoRepository();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscarPorId();
                case 4 -> atualizar();
                case 5 -> remover();
                case 0 -> System.out.println("\nEncerrando o sistema... Até logo!");
                default -> System.out.println("❌ Opção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n========= GERENCIADOR DE ESTOQUE =========");
        System.out.println("[1] Cadastrar novo medicamento");
        System.out.println("[2] Listar todos os medicamentos");
        System.out.println("[3] Buscar medicamento por ID");
        System.out.println("[4] Atualizar medicamento");
        System.out.println("[5] Remover medicamento");
        System.out.println("[0] Sair do programa");
        System.out.println("==========================================");
    }

    private static void cadastrar() {
        System.out.println("\n--- Cadastrar Medicamento ---");
        int id = lerInteiro("Digite o ID único: ");

        if (repository.buscarPorId(id).isPresent()) {
            System.out.println("❌ Erro: Já existe um registro cadastrado com o ID " + id);
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        double preco = lerDouble("Preço (R$): ");
        int qtd = lerInteiro("Quantidade em Estoque: ");

        Medicamento med = new Medicamento(id, nome, categoria, preco, qtd);
        if (repository.cadastrar(med)) {
            System.out.println("✅ Medicamento cadastrado com sucesso!");
        } else {
            System.out.println("❌ Erro ao cadastrar o medicamento.");
        }
    }

    private static void listar() {
        System.out.println("\n--- Lista de Medicamentos ---");
        if (repository.estaVazio()) {
            System.out.println("⚠️ Nenhum medicamento cadastrado no momento.");
            return;
        }
        List<Medicamento> lista = repository.listarTodos();
        lista.forEach(System.out::println);
    }

    private static void buscarPorId() {
        System.out.println("\n--- Buscar por ID ---");
        if (repository.estaVazio()) {
            System.out.println("⚠️ O repositório está vazio.");
            return;
        }
        int id = lerInteiro("Digite o ID do medicamento: ");
        Optional<Medicamento> med = repository.buscarPorId(id);

        med.ifPresentOrElse(
                m -> System.out.println("✅ Encontrado: " + m),
                () -> System.out.println("❌ Registro não encontrado para o ID " + id)
        );
    }

    private static void atualizar() {
        System.out.println("\n--- Atualizar Medicamento ---");
        int id = lerInteiro("Digite o ID do medicamento que deseja atualizar: ");

        if (repository.buscarPorId(id).isEmpty()) {
            System.out.println("❌ Erro: Registro não encontrado para o ID " + id);
            return;
        }

        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova Categoria: ");
        String categoria = scanner.nextLine();
        double preco = lerDouble("Novo Preço (R$): ");
        int qtd = lerInteiro("Nova Quantidade em Estoque: ");

        Medicamento novosDados = new Medicamento(id, nome, categoria, preco, qtd);
        if (repository.atualizar(id, novosDados)) {
            System.out.println("✅ Registro atualizado com sucesso!");
        } else {
            System.out.println("❌ Falha ao atualizar registro.");
        }
    }

    private static void remover() {
        System.out.println("\n--- Remover Medicamento ---");
        int id = lerInteiro("Digite o ID do medicamento para remover: ");

        if (repository.remover(id)) {
            System.out.println("✅ Registro removido com sucesso!");
        } else {
            System.out.println("❌ Erro: Registro não encontrado com o ID " + id);
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor < 0) {
                    System.out.println("⚠️ Por favor, insira um valor positivo.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("⚠️️ Entrada inválida! Digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
                if (valor < 0) {
                    System.out.println("⚠️ Por favor, insira um valor maior ou igual a zero.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida! Digite um valor numérico válido (ex: 12.50).");
            }
        }
    }
}