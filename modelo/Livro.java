package modelo;

import java.util.Objects;

/**
 * Classe que representa um livro na biblioteca
 */
public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private int ano;
    private String editora;
    private boolean disponivel;
    
    public Livro(String titulo, String autor, String isbn, int ano, String editora) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.editora = editora;
        this.disponivel = true;
    }
    
    // Getters e Setters com encapsulamento
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    
    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }
    
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    
    @Override
    public String toString() {
        return String.format("ISBN: %s | Título: %s | Autor: %s | Editora: %s | Status: %s",
                isbn, titulo, autor, editora, disponivel ? "Disponível" : "Emprestado");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Livro livro = (Livro) obj;
        return Objects.equals(isbn, livro.isbn);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}