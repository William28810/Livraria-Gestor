package model;

import java.time.LocalDate;

/**
 * Representa um livro cadastrado no sistema da livraria.
 * Contém informações básicas utilizadas para controle,
 * consulta e venda de livros.
 */
public class Livro {
    
    private int idLivro;
    private String titulo;
    private String autor;
    private String genero;
    private LocalDate dataPublicacao;
    private int quantidade;
    private double preco;
    private String observacao;
    
    /**
     * Construção da classe Livro
     */
    public Livro (){
        
    }
    
    public Livro (String titulo, String autor, String genero, LocalDate dataPublicacao, 
                  int quantidade, double preco, String observacao) {
        
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.dataPublicacao = dataPublicacao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.observacao = observacao;
    }
    
    // Getters e Setters
    
    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    /**
     * Retornar um representação textual do livro,
     * facilitando a visualização no console.
     */
    @Override
    public String toString(){
       return "Livro {\n" +
               "Título = '" + titulo + "\n" +
               "Autor = '" + autor + "\n" +
               "Gênero = '" + genero + "\n" +
               "Publicação = " + dataPublicacao + "\n" +
               "Qunatidade = " + quantidade + "\n" +
               "Preço = R$" + preco + "\n" +
               "Observação = '" + observacao + "\n" +
               '}';
    }
}
