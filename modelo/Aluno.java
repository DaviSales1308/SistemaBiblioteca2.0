package modelo;

/**
 * Classe Aluno que herda de Usuario
 */
public class Aluno extends Usuario {
    
    public Aluno(String nome, String matricula, String cpf, String email) {
        super(nome, matricula, cpf, email, "Aluno");
    }
    
    @Override
    public int getLimiteEmprestimos() {
        return 3; // Alunos podem emprestar até 3 livros
    }
    
    @Override
    public int getPrazoEmprestimo() {
        return 7; // Prazo de 7 dias para alunos
    }
}