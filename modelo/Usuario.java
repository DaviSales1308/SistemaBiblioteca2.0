package modelo;

import java.util.Objects;

/**
 * Classe base para usuários da biblioteca (aplicação de herança)
 */
public abstract class Usuario {
    protected String nome;
    protected String matricula;
    protected String cpf;
    protected String email;
    protected String tipo;
    
    public Usuario(String nome, String matricula, String cpf, String email, String tipo) {
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
        this.tipo = tipo;
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTipo() { return tipo; }
    
    // Métodos abstratos para aplicação de polimorfismo
    public abstract int getLimiteEmprestimos();
    public abstract int getPrazoEmprestimo();
    
    @Override
    public String toString() {
        return String.format("%s - %s (%s) - %s", matricula, nome, tipo, email);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return Objects.equals(matricula, usuario.matricula);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}