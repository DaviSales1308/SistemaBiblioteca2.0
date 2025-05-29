package modelo;

/**
 * Classe Professor que herda de Usuario
 */
public class Professor extends Usuario {
    
    public Professor(String nome, String matricula, String cpf, String email) {
        super(nome, matricula, cpf, email, "Professor");
    }
    
    @Override
    public int getLimiteEmprestimos() {
        return 5; // Professores podem emprestar até 5 livros
    }
    
    @Override
    public int getPrazoEmprestimo() {
        return 14; // Prazo de 14 dias para professores
    }
}