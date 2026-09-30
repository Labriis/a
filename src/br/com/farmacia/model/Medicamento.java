package br.com.farmacia.model;


public class Medicamento {
    private Integer id;
    private String nome;
    private String categoria;
    private double preco;
    private int quantidadeEstoque;

    public Medicamento(Integer id, String nome, String categoria, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s | Categoria: %s | Preço: R$ %.2f | Estoque: %d un.",
                id, nome, categoria, preco, quantidadeEstoque);
    }
}