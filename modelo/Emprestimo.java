package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um empréstimo
 */
public class Emprestimo {
    private int numeroEmprestimo;
    private Usuario usuario;
    private List<Livro> livros;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status; // "ATIVO", "DEVOLVIDO", "ATRASADO"
    
    public Emprestimo(int numeroEmprestimo, Usuario usuario, List<Livro> livros) {
        this.numeroEmprestimo = numeroEmprestimo;
        this.usuario = usuario;
        this.livros = new ArrayList<>(livros);
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(usuario.getPrazoEmprestimo());
        this.status = "ATIVO";
    }
    
    // Getters e Setters
    public int getNumeroEmprestimo() { return numeroEmprestimo; }
    
    public Usuario getUsuario() { return usuario; }
    
    public List<Livro> getLivros() { return new ArrayList<>(livros); }
    
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    /**
     * Verifica se o empréstimo está atrasado
     * @return true se estiver atrasado, false caso contrário
     */
    public boolean isAtrasado() {
        return LocalDate.now().isAfter(dataDevolucao) && "ATIVO".equals(status);
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Empréstimo #%d - %s - Data: %s - Devolução: %s - Status: %s",
                numeroEmprestimo, usuario.getNome(), 
                dataEmprestimo.format(formatter),
                dataDevolucao.format(formatter), status);
    }
}