package servico;

import modelo.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe principal que gerencia o sistema da biblioteca
 * Contém toda a lógica de negócio do sistema
 */
public class SistemaBiblioteca {
    private Map<String, Livro> acervo; // ISBN -> Livro
    private Map<String, Usuario> usuarios; // Matrícula -> Usuario
    private Map<Integer, Emprestimo> emprestimos; // Número -> Empréstimo
    private int proximoNumeroEmprestimo;
    
    public SistemaBiblioteca() {
        this.acervo = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.emprestimos = new HashMap<>();
        this.proximoNumeroEmprestimo = 1;
        inicializarDadosExemplo();
    }
    
    /**
     * Inicializa alguns dados de exemplo para demonstração
     */
    private void inicializarDadosExemplo() {
        // Cadastrar alguns livros
        cadastrarLivro("Java: Como Programar", "Deitel", "978-8543004792", 2017, "Pearson");
        cadastrarLivro("Clean Code", "Robert Martin", "978-0132350884", 2008, "Prentice Hall");
        cadastrarLivro("Design Patterns", "Gang of Four", "978-0201633610", 1994, "Addison-Wesley");
        cadastrarLivro("Estruturas de Dados e Algoritmos", "Thomas Cormen", "978-8535236996", 2012, "Campus");
        
        // Cadastrar alguns usuários
        cadastrarAluno("João Silva", "2023001", "123.456.789-00", "joao@universidade.edu");
        cadastrarProfessor("Dr. Maria Santos", "PROF001", "987.654.321-00", "maria@universidade.edu");
        cadastrarAluno("Ana Costa", "2023002", "111.222.333-44", "ana@universidade.edu");
    }
    
    // ========== MÉTODOS PARA CADASTRO DE LIVROS ==========
    
    /**
     * Cadastra um novo livro no sistema
     * @param titulo Título do livro
     * @param autor Autor do livro
     * @param isbn ISBN do livro (chave única)
     * @param ano Ano de publicação
     * @param editora Editora do livro
     * @return true se cadastrado com sucesso, false se já existe
     */
    public boolean cadastrarLivro(String titulo, String autor, String isbn, int ano, String editora) {
        if (acervo.containsKey(isbn)) {
            return false; // Livro já existe
        }
        Livro livro = new Livro(titulo, autor, isbn, ano, editora);
        acervo.put(isbn, livro);
        return true;
    }
    
    // ========== MÉTODOS PARA CADASTRO DE USUÁRIOS ==========
    
    /**
     * Cadastra um novo aluno no sistema
     */
    public boolean cadastrarAluno(String nome, String matricula, String cpf, String email) {
        if (usuarios.containsKey(matricula)) {
            return false; // Usuário já existe
        }
        Usuario aluno = new Aluno(nome, matricula, cpf, email);
        usuarios.put(matricula, aluno);
        return true;
    }
    
    /**
     * Cadastra um novo professor no sistema
     */
    public boolean cadastrarProfessor(String nome, String matricula, String cpf, String email) {
        if (usuarios.containsKey(matricula)) {
            return false; // Usuário já existe
        }
        Usuario professor = new Professor(nome, matricula, cpf, email);
        usuarios.put(matricula, professor);
        return true;
    }
    
    // ========== MÉTODOS DE CONSULTA DE LIVROS ==========
    
    /**
     * Retorna lista de livros disponíveis para empréstimo
     */
    public List<Livro> consultarLivrosDisponiveis() {
        return acervo.values().stream()
                .filter(Livro::isDisponivel)
                .collect(Collectors.toList());
    }
    
    /**
     * Busca livros por título (busca parcial, case-insensitive)
     */
    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        return acervo.values().stream()
                .filter(livro -> livro.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca livro específico por ISBN
     */
    public Livro buscarLivroPorIsbn(String isbn) {
        return acervo.get(isbn);
    }
    
    // ========== OPERAÇÕES DE EMPRÉSTIMO ==========
    
    /**
     * Realiza um empréstimo de livros para um usuário
     * @param matriculaUsuario Matrícula do usuário
     * @param isbns Lista de ISBNs dos livros a serem emprestados
     * @return Mensagem de sucesso ou erro
     */
    public String realizarEmprestimo(String matriculaUsuario, List<String> isbns) {
        // Verificar se usuário existe
        Usuario usuario = usuarios.get(matriculaUsuario);
        if (usuario == null) {
            return "Usuário não encontrado!";
        }
        
        // Verificar limite de empréstimos do usuário
        long emprestimosAtivos = emprestimos.values().stream()
                .filter(emp -> emp.getUsuario().getMatricula().equals(matriculaUsuario))
                .filter(emp -> "ATIVO".equals(emp.getStatus()))
                .count();
        
        if (emprestimosAtivos + isbns.size() > usuario.getLimiteEmprestimos()) {
            return "Limite de empréstimos excedido! Limite: " + usuario.getLimiteEmprestimos();
        }
        
        // Verificar disponibilidade dos livros
        List<Livro> livrosEmprestimo = new ArrayList<>();
        for (String isbn : isbns) {
            Livro livro = acervo.get(isbn);
            if (livro == null) {
                return "Livro com ISBN " + isbn + " não encontrado!";
            }
            if (!livro.isDisponivel()) {
                return "Livro '" + livro.getTitulo() + "' não está disponível!";
            }
            livrosEmprestimo.add(livro);
        }
        
        // Realizar o empréstimo
        Emprestimo emprestimo = new Emprestimo(proximoNumeroEmprestimo++, usuario, livrosEmprestimo);
        emprestimos.put(emprestimo.getNumeroEmprestimo(), emprestimo);
        
        // Marcar livros como indisponíveis
        for (Livro livro : livrosEmprestimo) {
            livro.setDisponivel(false);
        }
        
        return "Empréstimo realizado com sucesso! Número: " + emprestimo.getNumeroEmprestimo();
    }
    
    // ========== OPERAÇÕES DE DEVOLUÇÃO ==========
    
    /**
     * Realiza a devolução de um empréstimo
     * @param numeroEmprestimo Número do empréstimo a ser devolvido
     * @return Mensagem de sucesso ou erro
     */
    public String realizarDevolucao(int numeroEmprestimo) {
        Emprestimo emprestimo = emprestimos.get(numeroEmprestimo);
        if (emprestimo == null) {
            return "Empréstimo não encontrado!";
        }
        
        if (!"ATIVO".equals(emprestimo.getStatus())) {
            return "Este empréstimo já foi devolvido!";
        }
        
        // Marcar livros como disponíveis
        for (Livro livro : emprestimo.getLivros()) {
            livro.setDisponivel(true);
        }
        
        // Atualizar status do empréstimo
        emprestimo.setStatus("DEVOLVIDO");
        
        String mensagem = "Devolução realizada com sucesso!";
        if (emprestimo.isAtrasado()) {
            mensagem += " (ATENÇÃO: Devolução em atraso!)";
        }
        
        return mensagem;
    }
    
    // ========== MÉTODOS DE LISTAGEM E RELATÓRIOS ==========
    
    public List<Livro> listarTodosLivros() {
        return new ArrayList<>(acervo.values());
    }
    
    public List<Usuario> listarTodosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
    
    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimos.values().stream()
                .filter(emp -> "ATIVO".equals(emp.getStatus()))
                .collect(Collectors.toList());
    }
    
    public List<Emprestimo> listarEmprestimosAtrasados() {
        return emprestimos.values().stream()
                .filter(Emprestimo::isAtrasado)
                .collect(Collectors.toList());
    }
    
    public List<Emprestimo> listarEmprestimosPorUsuario(String matricula) {
        return emprestimos.values().stream()
                .filter(emp -> emp.getUsuario().getMatricula().equals(matricula))
                .collect(Collectors.toList());
    }
}