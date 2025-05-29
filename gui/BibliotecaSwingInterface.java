package gui;

import modelo.*;
import servico.SistemaBiblioteca;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.border.TitledBorder;
import java.time.format.DateTimeFormatter;

/**
 * Interface gráfica principal do sistema usando Swing
 */
public class BibliotecaSwingInterface extends JFrame {
    private SistemaBiblioteca sistema;
    private JTabbedPane tabbedPane;
    
    // Painéis principais
    private JPanel painelLivros;
    private JPanel painelUsuarios;
    private JPanel painelEmprestimos;
    private JPanel painelRelatorios;
    
    // Tabelas para exibição de dados
    private JTable tabelaLivros;
    private JTable tabelaUsuarios;
    private JTable tabelaEmprestimos;
    private DefaultTableModel modeloTabelaLivros;
    private DefaultTableModel modeloTabelaUsuarios;
    private DefaultTableModel modeloTabelaEmprestimos;
    
    // Labels para estatísticas
    private JLabel lblTotalLivrosValor;
    private JLabel lblLivrosDisponiveisValor;
    private JLabel lblTotalUsuariosValor;
    private JLabel lblEmprestimosAtivosValor;
    private JLabel lblEmprestimosAtrasadosValor;
    
    public BibliotecaSwingInterface() {
        this.sistema = new SistemaBiblioteca();
        inicializarInterface();
        atualizarTabelas();
    }
    
    private void inicializarInterface() {
        setTitle("Sistema de Gestão da Biblioteca Acadêmica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Usar o Look and Feel padrão do Swing (sempre funciona)
        // Comentado para evitar problemas de compatibilidade
        /*
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            System.err.println("Usando Look and Feel padrão");
        }
        */
        
        // Configurar layout principal
        setLayout(new BorderLayout());
        
        // Criar barra de menu
        criarBarraMenu();
        
        // Criar painel principal com abas
        criarPainelPrincipal();
        
        // Adicionar barra de status
        criarBarraStatus();
    }
    
    private void criarBarraMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Sistema
        JMenu menuSistema = new JMenu("Sistema");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        JMenuItem itemSair = new JMenuItem("Sair");
        
        itemSobre.addActionListener(e -> mostrarSobre());
        itemSair.addActionListener(e -> System.exit(0));
        
        menuSistema.add(itemSobre);
        menuSistema.addSeparator();
        menuSistema.add(itemSair);
        
        // Menu Ajuda
        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemAjuda = new JMenuItem("Como usar");
        itemAjuda.addActionListener(e -> mostrarAjuda());
        menuAjuda.add(itemAjuda);
        
        menuBar.add(menuSistema);
        menuBar.add(menuAjuda);
        
        setJMenuBar(menuBar);
    }
    
    private void criarPainelPrincipal() {
        tabbedPane = new JTabbedPane();
        
        // Criar painéis para cada funcionalidade
        criarPainelLivros();
        criarPainelUsuarios();
        criarPainelEmprestimos();
        criarPainelRelatorios();
        
        // Adicionar abas
        tabbedPane.addTab("📚 Livros", painelLivros);
        tabbedPane.addTab("👥 Usuários", painelUsuarios);
        tabbedPane.addTab("📋 Empréstimos", painelEmprestimos);
        tabbedPane.addTab("📊 Relatórios", painelRelatorios);
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    // ========== PAINEL DE LIVROS ==========
    
    private void criarPainelLivros() {
        painelLivros = new JPanel(new BorderLayout());
        painelLivros.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Painel superior com botões
        JPanel painelBotoesLivros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCadastrarLivro = new JButton("➕ Cadastrar Livro");
        JButton btnBuscarLivro = new JButton("🔍 Buscar");
        JButton btnAtualizarLivros = new JButton("🔄 Atualizar");
        
        btnCadastrarLivro.addActionListener(e -> mostrarDialogoCadastroLivro());
        btnBuscarLivro.addActionListener(e -> mostrarDialogoBuscaLivro());
        btnAtualizarLivros.addActionListener(e -> atualizarTabelaLivros());
        
        painelBotoesLivros.add(btnCadastrarLivro);
        painelBotoesLivros.add(btnBuscarLivro);
        painelBotoesLivros.add(btnAtualizarLivros);
        
        // Tabela de livros
        String[] colunas = {"ISBN", "Título", "Autor", "Editora", "Ano", "Status"};
        modeloTabelaLivros = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabela não editável
            }
        };
        
        tabelaLivros = new JTable(modeloTabelaLivros);
        tabelaLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaLivros.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPaneLivros = new JScrollPane(tabelaLivros);
        scrollPaneLivros.setBorder(new TitledBorder("Lista de Livros"));
        
        painelLivros.add(painelBotoesLivros, BorderLayout.NORTH);
        painelLivros.add(scrollPaneLivros, BorderLayout.CENTER);
    }
    
    // ========== PAINEL DE USUÁRIOS ==========
    
    private void criarPainelUsuarios() {
        painelUsuarios = new JPanel(new BorderLayout());
        painelUsuarios.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Painel superior com botões
        JPanel painelBotoesUsuarios = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCadastrarAluno = new JButton("➕ Cadastrar Aluno");
        JButton btnCadastrarProfessor = new JButton("➕ Cadastrar Professor");
        JButton btnAtualizarUsuarios = new JButton("🔄 Atualizar");
        
        btnCadastrarAluno.addActionListener(e -> mostrarDialogoCadastroAluno());
        btnCadastrarProfessor.addActionListener(e -> mostrarDialogoCadastroProfessor());
        btnAtualizarUsuarios.addActionListener(e -> atualizarTabelaUsuarios());
        
        painelBotoesUsuarios.add(btnCadastrarAluno);
        painelBotoesUsuarios.add(btnCadastrarProfessor);
        painelBotoesUsuarios.add(btnAtualizarUsuarios);
        
        // Tabela de usuários
        String[] colunas = {"Matrícula", "Nome", "Tipo", "E-mail", "Limite", "Prazo"};
        modeloTabelaUsuarios = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaUsuarios = new JTable(modeloTabelaUsuarios);
        tabelaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaUsuarios.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPaneUsuarios = new JScrollPane(tabelaUsuarios);
        scrollPaneUsuarios.setBorder(new TitledBorder("Lista de Usuários"));
        
        painelUsuarios.add(painelBotoesUsuarios, BorderLayout.NORTH);
        painelUsuarios.add(scrollPaneUsuarios, BorderLayout.CENTER);
    }
    
    // ========== PAINEL DE EMPRÉSTIMOS ==========
    
    private void criarPainelEmprestimos() {
        painelEmprestimos = new JPanel(new BorderLayout());
        painelEmprestimos.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Painel superior com botões
        JPanel painelBotoesEmp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRealizarEmprestimo = new JButton("📤 Realizar Empréstimo");
        JButton btnRealizarDevolucao = new JButton("📥 Realizar Devolução");
        JButton btnAtualizarEmp = new JButton("🔄 Atualizar");
        
        btnRealizarEmprestimo.addActionListener(e -> mostrarDialogoEmprestimo());
        btnRealizarDevolucao.addActionListener(e -> mostrarDialogoDevolucao());
        btnAtualizarEmp.addActionListener(e -> atualizarTabelaEmprestimos());
        
        painelBotoesEmp.add(btnRealizarEmprestimo);
        painelBotoesEmp.add(btnRealizarDevolucao);
        painelBotoesEmp.add(btnAtualizarEmp);
        
        // Tabela de empréstimos
        String[] colunas = {"Nº Empréstimo", "Usuário", "Data Empréstimo", "Data Devolução", "Status"};
        modeloTabelaEmprestimos = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaEmprestimos = new JTable(modeloTabelaEmprestimos);
        tabelaEmprestimos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaEmprestimos.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPaneEmp = new JScrollPane(tabelaEmprestimos);
        scrollPaneEmp.setBorder(new TitledBorder("Lista de Empréstimos"));
        
        painelEmprestimos.add(painelBotoesEmp, BorderLayout.NORTH);
        painelEmprestimos.add(scrollPaneEmp, BorderLayout.CENTER);
    }
    
    // ========== PAINEL DE RELATÓRIOS ==========
    
    private void criarPainelRelatorios() {
        painelRelatorios = new JPanel(new GridLayout(2, 2, 10, 10));
        painelRelatorios.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Painel de estatísticas
        JPanel painelEstatisticas = new JPanel(new GridLayout(5, 2, 5, 5));
        painelEstatisticas.setBorder(new TitledBorder("📊 Estatísticas Gerais"));
        
        JLabel lblTotalLivros = new JLabel("Total de Livros:");
        lblTotalLivrosValor = new JLabel("0");
        JLabel lblLivrosDisponiveis = new JLabel("Livros Disponíveis:");
        lblLivrosDisponiveisValor = new JLabel("0");
        JLabel lblTotalUsuarios = new JLabel("Total de Usuários:");
        lblTotalUsuariosValor = new JLabel("0");
        JLabel lblEmprestimosAtivos = new JLabel("Empréstimos Ativos:");
        lblEmprestimosAtivosValor = new JLabel("0");
        JLabel lblEmprestimosAtrasados = new JLabel("Empréstimos Atrasados:");
        lblEmprestimosAtrasadosValor = new JLabel("0");
        
        painelEstatisticas.add(lblTotalLivros);
        painelEstatisticas.add(lblTotalLivrosValor);
        painelEstatisticas.add(lblLivrosDisponiveis);
        painelEstatisticas.add(lblLivrosDisponiveisValor);
        painelEstatisticas.add(lblTotalUsuarios);
        painelEstatisticas.add(lblTotalUsuariosValor);
        painelEstatisticas.add(lblEmprestimosAtivos);
        painelEstatisticas.add(lblEmprestimosAtivosValor);
        painelEstatisticas.add(lblEmprestimosAtrasados);
        painelEstatisticas.add(lblEmprestimosAtrasadosValor);
        
        // Painel de ações rápidas
        JPanel painelAcoes = new JPanel(new GridLayout(4, 1, 5, 5));
        painelAcoes.setBorder(new TitledBorder("⚡ Ações Rápidas"));
        
        JButton btnVerAtrasados = new JButton("⚠️ Ver Empréstimos Atrasados");
        JButton btnVerDisponiveis = new JButton("📚 Ver Livros Disponíveis");
        JButton btnEstatisticasCompletas = new JButton("📊 Atualizar Estatísticas");
        JButton btnLimparFiltros = new JButton("🔄 Limpar Todos os Filtros");
        
        btnVerAtrasados.addActionListener(e -> mostrarEmprestimosAtrasados());
        btnVerDisponiveis.addActionListener(e -> mostrarLivrosDisponiveis());
        btnEstatisticasCompletas.addActionListener(e -> atualizarEstatisticas());
        btnLimparFiltros.addActionListener(e -> atualizarTabelas());
        
        painelAcoes.add(btnVerAtrasados);
        painelAcoes.add(btnVerDisponiveis);
        painelAcoes.add(btnEstatisticasCompletas);
        painelAcoes.add(btnLimparFiltros);
        
        painelRelatorios.add(painelEstatisticas);
        painelRelatorios.add(painelAcoes);
    }
    
    private void criarBarraStatus() {
        JPanel barraStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraStatus.setBorder(BorderFactory.createLoweredBevelBorder());
        JLabel lblStatus = new JLabel("Sistema iniciado com sucesso!");
        barraStatus.add(lblStatus);
        add(barraStatus, BorderLayout.SOUTH);
    }
    
    // ========== MÉTODOS DE ATUALIZAÇÃO DAS TABELAS ==========
    
    private void atualizarTabelas() {
        atualizarTabelaLivros();
        atualizarTabelaUsuarios();
        atualizarTabelaEmprestimos();
        atualizarEstatisticas();
    }
    
    private void atualizarTabelaLivros() {
        modeloTabelaLivros.setRowCount(0); // Limpar tabela
        
        List<Livro> livros = sistema.listarTodosLivros();
        for (Livro livro : livros) {
            Object[] linha = {
                livro.getIsbn(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getEditora(),
                livro.getAno(),
                livro.isDisponivel() ? "Disponível" : "Emprestado"
            };
            modeloTabelaLivros.addRow(linha);
        }
    }
    
    private void atualizarTabelaUsuarios() {
        modeloTabelaUsuarios.setRowCount(0); // Limpar tabela
        
        List<Usuario> usuarios = sistema.listarTodosUsuarios();
        for (Usuario usuario : usuarios) {
            Object[] linha = {
                usuario.getMatricula(),
                usuario.getNome(),
                usuario.getTipo(),
                usuario.getEmail(),
                usuario.getLimiteEmprestimos() + " livros",
                usuario.getPrazoEmprestimo() + " dias"
            };
            modeloTabelaUsuarios.addRow(linha);
        }
    }
    
    private void atualizarTabelaEmprestimos() {
        modeloTabelaEmprestimos.setRowCount(0); // Limpar tabela
        
        List<Emprestimo> emprestimos = sistema.listarEmprestimosAtivos();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (Emprestimo emp : emprestimos) {
            Object[] linha = {
                emp.getNumeroEmprestimo(),
                emp.getUsuario().getNome(),
                emp.getDataEmprestimo().format(formatter),
                emp.getDataDevolucao().format(formatter),
                emp.isAtrasado() ? "ATRASADO" : emp.getStatus()
            };
            modeloTabelaEmprestimos.addRow(linha);
        }
    }
    
    private void atualizarEstatisticas() {
        lblTotalLivrosValor.setText(String.valueOf(sistema.listarTodosLivros().size()));
        lblLivrosDisponiveisValor.setText(String.valueOf(sistema.consultarLivrosDisponiveis().size()));
        lblTotalUsuariosValor.setText(String.valueOf(sistema.listarTodosUsuarios().size()));
        lblEmprestimosAtivosValor.setText(String.valueOf(sistema.listarEmprestimosAtivos().size()));
        lblEmprestimosAtrasadosValor.setText(String.valueOf(sistema.listarEmprestimosAtrasados().size()));
    }
    
    // ========== DIÁLOGOS DE CADASTRO ==========
    
    private void mostrarDialogoCadastroLivro() {
        JDialog dialog = new JDialog(this, "Cadastrar Livro", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Campos do formulário
        JTextField txtTitulo = new JTextField(20);
        JTextField txtAutor = new JTextField(20);
        JTextField txtIsbn = new JTextField(20);
        JTextField txtAno = new JTextField(20);
        JTextField txtEditora = new JTextField(20);
        
        // Layout do formulário
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        painel.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        painel.add(txtTitulo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        painel.add(txtAutor, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1;
        painel.add(txtIsbn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        painel.add(new JLabel("Ano:"), gbc);
        gbc.gridx = 1;
        painel.add(txtAno, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        painel.add(new JLabel("Editora:"), gbc);
        gbc.gridx = 1;
        painel.add(txtEditora, gbc);
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnCadastrar.addActionListener(e -> {
            try {
                String titulo = txtTitulo.getText().trim();
                String autor = txtAutor.getText().trim();
                String isbn = txtIsbn.getText().trim();
                String editora = txtEditora.getText().trim();
                int ano = Integer.parseInt(txtAno.getText().trim());
                
                if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || editora.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Todos os campos são obrigatórios!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (sistema.cadastrarLivro(titulo, autor, isbn, ano, editora)) {
                    JOptionPane.showMessageDialog(dialog, "Livro cadastrado com sucesso!", 
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    atualizarTabelaLivros();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Livro com este ISBN já existe!", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Ano deve ser um número válido!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnCancelar);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        painel.add(painelBotoes, gbc);
        
        dialog.add(painel);
        dialog.setVisible(true);
    }
    
    private void mostrarDialogoCadastroAluno() {
        mostrarDialogoCadastroUsuario("Cadastrar Aluno", true);
    }
    
    private void mostrarDialogoCadastroProfessor() {
        mostrarDialogoCadastroUsuario("Cadastrar Professor", false);
    }
    
    private void mostrarDialogoCadastroUsuario(String titulo, boolean isAluno) {
        JDialog dialog = new JDialog(this, titulo, true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);
        
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Campos do formulário
        JTextField txtNome = new JTextField(20);
        JTextField txtMatricula = new JTextField(20);
        JTextField txtCpf = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        
        // Layout do formulário
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        painel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        painel.add(txtNome, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Matrícula:"), gbc);
        gbc.gridx = 1;
        painel.add(txtMatricula, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        painel.add(txtCpf, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        painel.add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1;
        painel.add(txtEmail, gbc);
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String matricula = txtMatricula.getText().trim();
            String cpf = txtCpf.getText().trim();
            String email = txtEmail.getText().trim();
            
            if (nome.isEmpty() || matricula.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Todos os campos são obrigatórios!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean sucesso;
            if (isAluno) {
                sucesso = sistema.cadastrarAluno(nome, matricula, cpf, email);
            } else {
                sucesso = sistema.cadastrarProfessor(nome, matricula, cpf, email);
            }
            
            if (sucesso) {
                JOptionPane.showMessageDialog(dialog, 
                    (isAluno ? "Aluno" : "Professor") + " cadastrado com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarTabelaUsuarios();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Usuário com esta matrícula já existe!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnCancelar);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        painel.add(painelBotoes, gbc);
        
        dialog.add(painel);
        dialog.setVisible(true);
    }
    
    // ========== DIÁLOGOS DE EMPRÉSTIMO ==========
    
    private void mostrarDialogoEmprestimo() {
        JDialog dialog = new JDialog(this, "Realizar Empréstimo", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel superior - dados do usuário
        JPanel painelUsuario = new JPanel(new FlowLayout());
        painelUsuario.add(new JLabel("Matrícula do Usuário:"));
        JTextField txtMatricula = new JTextField(15);
        painelUsuario.add(txtMatricula);
        
        // Painel central - lista de ISBNs
        JPanel painelLivros = new JPanel(new BorderLayout());
        painelLivros.setBorder(new TitledBorder("Livros para Empréstimo"));
        
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> listaISBNs = new JList<>(modeloLista);
        JScrollPane scrollLista = new JScrollPane(listaISBNs);
        scrollLista.setPreferredSize(new Dimension(400, 200));
        
        JPanel painelBotoesLivros = new JPanel(new FlowLayout());
        JTextField txtISBN = new JTextField(15);
        JButton btnAdicionarISBN = new JButton("Adicionar ISBN");
        JButton btnRemoverISBN = new JButton("Remover Selecionado");
        
        btnAdicionarISBN.addActionListener(e -> {
            String isbn = txtISBN.getText().trim();
            if (!isbn.isEmpty() && !modeloLista.contains(isbn)) {
                modeloLista.addElement(isbn);
                txtISBN.setText("");
            }
        });
        
        btnRemoverISBN.addActionListener(e -> {
            int selectedIndex = listaISBNs.getSelectedIndex();
            if (selectedIndex != -1) {
                modeloLista.removeElementAt(selectedIndex);
            }
        });
        
        painelBotoesLivros.add(new JLabel("ISBN:"));
        painelBotoesLivros.add(txtISBN);
        painelBotoesLivros.add(btnAdicionarISBN);
        painelBotoesLivros.add(btnRemoverISBN);
        
        painelLivros.add(painelBotoesLivros, BorderLayout.NORTH);
        painelLivros.add(scrollLista, BorderLayout.CENTER);
        
        // Painel inferior - botões de ação
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnRealizarEmprestimo = new JButton("Realizar Empréstimo");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnRealizarEmprestimo.addActionListener(e -> {
            String matricula = txtMatricula.getText().trim();
            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Informe a matrícula do usuário!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (modeloLista.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Adicione pelo menos um livro!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            java.util.List<String> isbns = new java.util.ArrayList<>();
            for (int i = 0; i < modeloLista.size(); i++) {
                isbns.add(modeloLista.getElementAt(i));
            }
            
            String resultado = sistema.realizarEmprestimo(matricula, isbns);
            
            if (resultado.startsWith("Empréstimo realizado")) {
                JOptionPane.showMessageDialog(dialog, resultado, "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                atualizarTabelaEmprestimos();
                atualizarTabelaLivros();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, resultado, "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        painelBotoes.add(btnRealizarEmprestimo);
        painelBotoes.add(btnCancelar);
        
        painel.add(painelUsuario, BorderLayout.NORTH);
        painel.add(painelLivros, BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);
        
        dialog.add(painel);
        dialog.setVisible(true);
    }
    
    private void mostrarDialogoDevolucao() {
        String numeroStr = JOptionPane.showInputDialog(this, 
            "Digite o número do empréstimo:", "Realizar Devolução", 
            JOptionPane.QUESTION_MESSAGE);
        
        if (numeroStr != null && !numeroStr.trim().isEmpty()) {
            try {
                int numero = Integer.parseInt(numeroStr.trim());
                String resultado = sistema.realizarDevolucao(numero);
                
                if (resultado.startsWith("Devolução realizada")) {
                    JOptionPane.showMessageDialog(this, resultado, "Sucesso", 
                        JOptionPane.INFORMATION_MESSAGE);
                    atualizarTabelaEmprestimos();
                    atualizarTabelaLivros();
                } else {
                    JOptionPane.showMessageDialog(this, resultado, "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Número do empréstimo inválido!", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ========== DIÁLOGOS DE BUSCA E RELATÓRIOS ==========
    
    private void mostrarDialogoBuscaLivro() {
        String[] opcoes = {"Buscar por ISBN", "Buscar por Título"};
        int escolha = JOptionPane.showOptionDialog(this,
            "Como deseja buscar o livro?", "Buscar Livro",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, opcoes, opcoes[0]);
        
        if (escolha == 0) { // Buscar por ISBN
            String isbn = JOptionPane.showInputDialog(this, "Digite o ISBN:");
            if (isbn != null && !isbn.trim().isEmpty()) {
                Livro livro = sistema.buscarLivroPorIsbn(isbn.trim());
                if (livro != null) {
                    mostrarDetalhesLivro(livro);
                } else {
                    JOptionPane.showMessageDialog(this, "Livro não encontrado!", 
                        "Resultado", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (escolha == 1) { // Buscar por título
            String titulo = JOptionPane.showInputDialog(this, "Digite parte do título:");
            if (titulo != null && !titulo.trim().isEmpty()) {
                List<Livro> livros = sistema.buscarLivrosPorTitulo(titulo.trim());
                mostrarResultadosBusca(livros, "Busca por título: " + titulo);
            }
        }
    }
    
    private void mostrarDetalhesLivro(Livro livro) {
        String detalhes = String.format(
            "DETALHES DO LIVRO\n\n" +
            "ISBN: %s\n" +
            "Título: %s\n" +
            "Autor: %s\n" +
            "Editora: %s\n" +
            "Ano: %d\n" +
            "Status: %s",
            livro.getIsbn(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getEditora(),
            livro.getAno(),
            livro.isDisponivel() ? "Disponível" : "Emprestado"
        );
        
        JOptionPane.showMessageDialog(this, detalhes, "Detalhes do Livro", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarResultadosBusca(List<Livro> livros, String titulo) {
        if (livros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum livro encontrado!", 
                titulo, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Criar janela com tabela de resultados
        JDialog dialog = new JDialog(this, titulo, true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        
        String[] colunas = {"ISBN", "Título", "Autor", "Status"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (Livro livro : livros) {
            Object[] linha = {
                livro.getIsbn(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.isDisponivel() ? "Disponível" : "Emprestado"
            };
            modelo.addRow(linha);
        }
        
        JTable tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dialog.dispose());
        
        JPanel painelBotao = new JPanel(new FlowLayout());
        painelBotao.add(btnFechar);
        
        dialog.add(scroll, BorderLayout.CENTER);
        dialog.add(painelBotao, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private void mostrarEmprestimosAtrasados() {
        List<Emprestimo> atrasados = sistema.listarEmprestimosAtrasados();
        
        if (atrasados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum empréstimo em atraso!", 
                "Empréstimos Atrasados", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Criar janela com tabela de empréstimos atrasados
        JDialog dialog = new JDialog(this, "Empréstimos Atrasados", true);
        dialog.setSize(700, 400);
        dialog.setLocationRelativeTo(this);
        
        String[] colunas = {"Nº", "Usuário", "Data Empréstimo", "Data Devolução", "Dias Atraso"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Emprestimo emp : atrasados) {
            long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(
                emp.getDataDevolucao(), java.time.LocalDate.now());
            
            Object[] linha = {
                emp.getNumeroEmprestimo(),
                emp.getUsuario().getNome(),
                emp.getDataEmprestimo().format(formatter),
                emp.getDataDevolucao().format(formatter),
                diasAtraso + " dias"
            };
            modelo.addRow(linha);
        }
        
        JTable tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dialog.dispose());
        
        JPanel painelBotao = new JPanel(new FlowLayout());
        painelBotao.add(btnFechar);
        
        dialog.add(scroll, BorderLayout.CENTER);
        dialog.add(painelBotao, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private void mostrarLivrosDisponiveis() {
        List<Livro> disponiveis = sistema.consultarLivrosDisponiveis();
        
        if (disponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum livro disponível no momento!", 
                "Livros Disponíveis", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        mostrarResultadosBusca(disponiveis, "Livros Disponíveis (" + disponiveis.size() + ")");
    }
    
    // ========== MÉTODOS AUXILIARES ==========
    
    private void mostrarSobre() {
        String sobre = "SISTEMA DE GESTÃO DA BIBLIOTECA ACADÊMICA\n\n" +
                      "Versão: 1.0\n" +
                      "Desenvolvido em Java com Swing\n\n" +
                      "Funcionalidades:\n" +
                      "• Cadastro de livros e usuários\n" +
                      "• Sistema de empréstimos\n" +
                      "• Controle de devoluções\n" +
                      "• Relatórios e estatísticas\n\n" +
                      "Aplicação dos conceitos de POO:\n" +
                      "• Encapsulamento\n" +
                      "• Herança\n" +
                      "• Polimorfismo\n" +
                      "• Collections";
        
        JOptionPane.showMessageDialog(this, sobre, "Sobre o Sistema", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarAjuda() {
        String ajuda = "COMO USAR O SISTEMA\n\n" +
                      "1. ABA LIVROS:\n" +
                      "   • Cadastre novos livros clicando em 'Cadastrar Livro'\n" +
                      "   • Use 'Buscar' para encontrar livros específicos\n\n" +
                      "2. ABA USUÁRIOS:\n" +
                      "   • Cadastre alunos e professores\n" +
                      "   • Professores têm mais privilégios que alunos\n\n" +
                      "3. ABA EMPRÉSTIMOS:\n" +
                      "   • Realize empréstimos informando a matrícula\n" +
                      "   • Para devoluções, use o número do empréstimo\n\n" +
                      "4. ABA RELATÓRIOS:\n" +
                      "   • Visualize estatísticas do sistema\n" +
                      "   • Consulte empréstimos atrasados";
        
        JOptionPane.showMessageDialog(this, ajuda, "Ajuda", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // ========== MÉTODO MAIN E CLASSE PRINCIPAL ==========
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new BibliotecaSwingInterface().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Erro ao inicializar o sistema: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}