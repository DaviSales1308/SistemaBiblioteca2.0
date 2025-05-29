package interfacee;

import modelo.*;
import servico.SistemaBiblioteca;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Interface de console para interação com o sistema
 * Responsável por toda a interação com o usuário
 */
public class InterfaceBiblioteca {
    private SistemaBiblioteca sistema;
    private Scanner scanner;
    
    public InterfaceBiblioteca() {
        this.sistema = new SistemaBiblioteca();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Método principal que executa o sistema
     */
    public void executar() {
        System.out.println("=== SISTEMA DE GESTÃO DA BIBLIOTECA ACADÊMICA ===");
        System.out.println("Bem-vindo ao sistema!");
        
        while (true) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1: menuLivros(); break;
                case 2: menuUsuarios(); break;
                case 3: menuEmprestimos(); break;
                case 4: menuRelatorios(); break;
                case 0: 
                    System.out.println("Obrigado por usar o sistema!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    // ========== MÉTODOS DE MENU ==========
    
    private void exibirMenuPrincipal() {
        System.out.println("\n" + criarLinha("=", 50));
        System.out.println("MENU PRINCIPAL");
        System.out.println(criarLinha("=", 50));
        System.out.println("1. Gerenciar Livros");
        System.out.println("2. Gerenciar Usuários");
        System.out.println("3. Gerenciar Empréstimos");
        System.out.println("4. Relatórios");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private void menuLivros() {
        while (true) {
            System.out.println("\n" + criarLinha("=", 30));
            System.out.println("GERENCIAR LIVROS");
            System.out.println(criarLinha("=", 30));
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Buscar Livro por ISBN");
            System.out.println("3. Buscar Livros por Título");
            System.out.println("4. Listar Livros Disponíveis");
            System.out.println("5. Listar Todos os Livros");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1: cadastrarLivro(); break;
                case 2: buscarLivroPorIsbn(); break;
                case 3: buscarLivrosPorTitulo(); break;
                case 4: listarLivrosDisponiveis(); break;
                case 5: listarTodosLivros(); break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuUsuarios() {
        while (true) {
            System.out.println("\n" + criarLinha("=", 30));
            System.out.println("GERENCIAR USUÁRIOS");
            System.out.println(criarLinha("=", 30));
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Professor");
            System.out.println("3. Listar Todos os Usuários");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1: cadastrarAluno(); break;
                case 2: cadastrarProfessor(); break;
                case 3: listarTodosUsuarios(); break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuEmprestimos() {
        while (true) {
            System.out.println("\n" + criarLinha("=", 30));
            System.out.println("GERENCIAR EMPRÉSTIMOS");
            System.out.println(criarLinha("=", 30));
            System.out.println("1. Realizar Empréstimo");
            System.out.println("2. Realizar Devolução");
            System.out.println("3. Listar Empréstimos Ativos");
            System.out.println("4. Listar Empréstimos por Usuário");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1: realizarEmprestimo(); break;
                case 2: realizarDevolucao(); break;
                case 3: listarEmprestimosAtivos(); break;
                case 4: listarEmprestimosPorUsuario(); break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuRelatorios() {
        while (true) {
            System.out.println("\n" + criarLinha("=", 30));
            System.out.println("RELATÓRIOS");
            System.out.println(criarLinha("=", 30));
            System.out.println("1. Empréstimos Atrasados");
            System.out.println("2. Estatísticas Gerais");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1: listarEmprestimosAtrasados(); break;
                case 2: exibirEstatisticas(); break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }
    
    // ========== MÉTODOS DE CADASTRO ==========
    
    private void cadastrarLivro() {
        System.out.println("\n--- CADASTRAR LIVRO ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = lerInteiro();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        
        if (sistema.cadastrarLivro(titulo, autor, isbn, ano, editora)) {
            System.out.println("✅ Livro cadastrado com sucesso!");
        } else {
            System.out.println("❌ Erro: Livro com este ISBN já existe!");
        }
    }
    
    private void cadastrarAluno() {
        System.out.println("\n--- CADASTRAR ALUNO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        
        if (sistema.cadastrarAluno(nome, matricula, cpf, email)) {
            System.out.println("✅ Aluno cadastrado com sucesso!");
        } else {
            System.out.println("❌ Erro: Usuário com esta matrícula já existe!");
        }
    }
    
    private void cadastrarProfessor() {
        System.out.println("\n--- CADASTRAR PROFESSOR ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        
        if (sistema.cadastrarProfessor(nome, matricula, cpf, email)) {
            System.out.println("✅ Professor cadastrado com sucesso!");
        } else {
            System.out.println("❌ Erro: Usuário com esta matrícula já existe!");
        }
    }
    
    // ========== MÉTODOS DE CONSULTA ==========
    
    private void buscarLivroPorIsbn() {
        System.out.print("Digite o ISBN: ");
        String isbn = scanner.nextLine();
        Livro livro = sistema.buscarLivroPorIsbn(isbn);
        
        if (livro != null) {
            System.out.println("\n📚 Livro encontrado:");
            System.out.println(livro);
        } else {
            System.out.println("❌ Livro não encontrado!");
        }
    }
    
    private void buscarLivrosPorTitulo() {
        System.out.print("Digite parte do título: ");
        String titulo = scanner.nextLine();
        List<Livro> livros = sistema.buscarLivrosPorTitulo(titulo);
        
        if (livros.isEmpty()) {
            System.out.println("❌ Nenhum livro encontrado!");
        } else {
            System.out.println("\n📚 Livros encontrados:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }
    
    private void listarLivrosDisponiveis() {
        List<Livro> livros = sistema.consultarLivrosDisponiveis();
        
        if (livros.isEmpty()) {
            System.out.println("❌ Nenhum livro disponível no momento!");
        } else {
            System.out.println("\n📚 Livros disponíveis:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }
    
    private void listarTodosLivros() {
        List<Livro> livros = sistema.listarTodosLivros();
        
        if (livros.isEmpty()) {
            System.out.println("❌ Nenhum livro cadastrado!");
        } else {
            System.out.println("\n📚 Todos os livros:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }
    
    private void listarTodosUsuarios() {
        List<Usuario> usuarios = sistema.listarTodosUsuarios();
        
        if (usuarios.isEmpty()) {
            System.out.println("❌ Nenhum usuário cadastrado!");
        } else {
            System.out.println("\n👥 Todos os usuários:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario + 
                    " | Limite: " + usuario.getLimiteEmprestimos() + " livros" +
                    " | Prazo: " + usuario.getPrazoEmprestimo() + " dias");
            }
        }
    }
    
    // ========== MÉTODOS DE EMPRÉSTIMO E DEVOLUÇÃO ==========
    
    private void realizarEmprestimo() {
        System.out.println("\n--- REALIZAR EMPRÉSTIMO ---");
        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine();
        
        System.out.print("Quantos livros deseja emprestar? ");
        int quantidade = lerInteiro();
        
        List<String> isbns = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            System.out.print("ISBN do livro " + i + ": ");
            isbns.add(scanner.nextLine());
        }
        
        String resultado = sistema.realizarEmprestimo(matricula, isbns);
        System.out.println(resultado.startsWith("Empréstimo realizado") ? "✅ " + resultado : "❌ " + resultado);
    }
    
    private void realizarDevolucao() {
        System.out.println("\n--- REALIZAR DEVOLUÇÃO ---");
        System.out.print("Número do empréstimo: ");
        int numero = lerInteiro();
        
        String resultado = sistema.realizarDevolucao(numero);
        System.out.println(resultado.startsWith("Devolução realizada") ? "✅ " + resultado : "❌ " + resultado);
    }
    
    private void listarEmprestimosAtivos() {
        List<Emprestimo> emprestimos = sistema.listarEmprestimosAtivos();
        
        if (emprestimos.isEmpty()) {
            System.out.println("❌ Nenhum empréstimo ativo!");
        } else {
            System.out.println("\n📋 Empréstimos ativos:");
            for (Emprestimo emp : emprestimos) {
                System.out.println(emp);
                System.out.println("   Livros: " + emp.getLivros().stream()
                    .map(Livro::getTitulo)
                    .collect(Collectors.joining(", ")));
            }
        }
    }
    
    private void listarEmprestimosPorUsuario() {
        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine();
        
        List<Emprestimo> emprestimos = sistema.listarEmprestimosPorUsuario(matricula);
        
        if (emprestimos.isEmpty()) {
            System.out.println("❌ Nenhum empréstimo encontrado para este usuário!");
        } else {
            System.out.println("\n📋 Empréstimos do usuário:");
            for (Emprestimo emp : emprestimos) {
                System.out.println(emp);
            }
        }
    }
    
    private void listarEmprestimosAtrasados() {
        List<Emprestimo> atrasados = sistema.listarEmprestimosAtrasados();
        
        if (atrasados.isEmpty()) {
            System.out.println("✅ Nenhum empréstimo em atraso!");
        } else {
            System.out.println("\n⚠️ Empréstimos atrasados:");
            for (Emprestimo emp : atrasados) {
                System.out.println(emp);
            }
        }
    }
    
    private void exibirEstatisticas() {
        System.out.println("\n📊 ESTATÍSTICAS GERAIS");
        System.out.println(criarLinha("=", 30));
        System.out.println("Total de livros: " + sistema.listarTodosLivros().size());
        System.out.println("Livros disponíveis: " + sistema.consultarLivrosDisponiveis().size());
        System.out.println("Total de usuários: " + sistema.listarTodosUsuarios().size());
        System.out.println("Empréstimos ativos: " + sistema.listarEmprestimosAtivos().size());
        System.out.println("Empréstimos atrasados: " + sistema.listarEmprestimosAtrasados().size());
    }
    
    // ========== MÉTODOS AUXILIARES ==========
    
    /**
     * Lê uma opção do usuário (número inteiro)
     */
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Retorna valor inválido para tratar no switch
        }
    }
    
    /**
     * Lê um número inteiro do usuário com validação
     */
    private int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, digite um número válido: ");
            }
        }
    }
    
    /**
     * Cria uma linha de caracteres repetidos (compatível com Java 8)
     * Substitui o método String.repeat() que só existe no Java 11+
     */
    private String criarLinha(String caractere, int quantidade) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quantidade; i++) {
            sb.append(caractere);
        }
        return sb.toString();
    }
}