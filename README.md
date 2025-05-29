# 📚 Sistema de Gestão de Biblioteca Acadêmica - Interface Gráfica

<div align="center">

![Java](https://img.shields.io/badge/Java-8%2B-orange?style=for-the-badge&logo=java)
![Swing](https://img.shields.io/badge/GUI-Swing-blue?style=for-the-badge&logo=java)
![POO](https://img.shields.io/badge/Paradigma-POO-green?style=for-the-badge&logo=java)
![Desktop](https://img.shields.io/badge/Platform-Desktop-purple?style=for-the-badge&logo=desktop)
![Status](https://img.shields.io/badge/Status-Funcionando-success?style=for-the-badge)

*Sistema completo de gestão de biblioteca com interface gráfica moderna e intuitiva, desenvolvido em Java Swing aplicando conceitos avançados de POO e design patterns.*

[🖼️ Screenshots](#️-screenshots) • [🚀 Como Executar](#-como-executar) • [💻 Funcionalidades](#-funcionalidades) • [🏗️ Arquitetura](#️-arquitetura) • [📱 Interface](#-interface)

</div>

---

## 🎯 **Visão Geral**

Esta é a **versão gráfica** do Sistema de Gestão de Biblioteca Acadêmica, oferecendo uma experiência visual moderna e intuitiva para gerenciar livros, usuários e empréstimos. O projeto mantém toda a robustez da lógica de negócio orientada a objetos, agora com uma interface profissional desenvolvida em **Java Swing**.

### **🌟 Por que Swing?**
- 🖥️ **Nativo Java** - Funciona em qualquer sistema com JVM
- 🎨 **Interface Rica** - Componentes visuais avançados
- ⚡ **Performance** - Execução rápida e responsiva  
- 🔧 **Facilidade** - Não requer bibliotecas externas
- 🌍 **Multiplataforma** - Windows, Linux, macOS

---

## 🚀 **Como Executar**

### **📋 Pré-requisitos**
- ☕ **Java 8** ou superior
- 🖥️ **Sistema Operacional:** Windows, Linux ou macOS
- 💾 **Memória:** Mínimo 512MB RAM
- 🖱️ **Interface gráfica** habilitada

### **⚡ Execução Rápida**

```bash
# 1. Clone o repositório
git clone [https://github.com/seu-usuario/biblioteca-swing.git](https://github.com/DaviSales1308/SistemaBiblioteca2.0)
cd biblioteca-swing

# 2. Compile o projeto
javac -d bin src/biblioteca/modelo/*.java src/biblioteca/servico/*.java src/biblioteca/gui/*.java src/biblioteca/principal/*.java

# 3. Execute a interface gráfica
java -cp bin principal.MainSwing
```
---

## 🏗️ **Arquitetura do Projeto**

```
📁 biblioteca/
├── 📂 modelo/                     # 🎯 Camada de Domínio
│   ├── 📄 Livro.java             #    📚 Entidade Livro
│   ├── 📄 Usuario.java           #    👤 Classe abstrata Usuario
│   ├── 📄 Aluno.java             #    🎓 Herança: Aluno extends Usuario
│   ├── 📄 Professor.java         #    👨‍🏫 Herança: Professor extends Usuario
│   └── 📄 Emprestimo.java        #    📋 Entidade Emprestimo
├── 📂 servico/                    # 🔧 Camada de Negócio
│   └── 📄 SistemaBiblioteca.java #    🏛️ Lógica de negócio central
├── 📂 gui/                        # 🎨 Camada de Apresentação
│   └── 📄 BibliotecaSwingInterface.java # 🖼️ Interface gráfica Swing
├── 📂 interface_/                 # 💻 Interface Console (alternativa)
│   └── 📄 InterfaceBiblioteca.java #   🖥️ Interface texto para comparação
└── 📂 principal/                  # 🚀 Pontos de Entrada
    ├── 📄 Main.java              #    ▶️ Executa versão console
    └── 📄 MainSwing.java         #    🖼️ Executa versão gráfica
```
---

## 💻 **Funcionalidades da Interface**

### **📚 Aba Livros**
- ➕ **Cadastro intuitivo** com formulário validado
- 🔍 **Busca avançada** por ISBN ou título
- 📋 **Tabela interativa** com todos os livros
- 🔄 **Atualização em tempo real** do status
- 🎨 **Cores diferenciadas** para disponibilidade

### **👥 Aba Usuários**  
- 👨‍🎓 **Cadastro de alunos** com validação específica
- 👨‍🏫 **Cadastro de professores** com privilégios diferenciados
- 📊 **Visualização clara** de limites e prazos
- 📋 **Lista organizada** com filtros

### **📋 Aba Empréstimos**
- 📤 **Empréstimo múltiplo** com interface drag-and-drop
- 📥 **Devolução simples** por número
- ⏰ **Controle visual** de datas e prazos
- ⚠️ **Alertas visuais** para empréstimos atrasados
- 📊 **Status colorido** (verde=ativo, vermelho=atrasado)

### **📊 Aba Relatórios**
- 📈 **Dashboard com estatísticas** em tempo real
- 📋 **Relatórios filtráveis** e exportáveis
- ⚠️ **Lista de atrasados** com detalhes
- 🔍 **Consultas personalizadas** interativas

---

## 📱 **Interface e Experiência do Usuário**

### **🎨 Design Principles**
- **🧭 Navegação Intuitiva:** Abas organizadas por funcionalidade
- **🎯 Ações Claras:** Botões com ícones descritivos
- **📊 Feedback Visual:** Mensagens de sucesso/erro imediatas
- **⚡ Responsividade:** Interface fluida e rápida
- **🔄 Consistência:** Padrão visual em toda aplicação

### **🖱️ Interações Principais**

#### **Cadastro de Livros:**
```
1. 📚 Clique na aba "Livros"
2. ➕ Clique em "Cadastrar Livro"  
3. 📝 Preencha o formulário
4. ✅ Clique em "Cadastrar"
5. 🎉 Sucesso! Livro adicionado à tabela
```

#### **Realizar Empréstimo:**
```
1. 📋 Vá para aba "Empréstimos"
2. 📤 Clique em "Realizar Empréstimo"
3. 👤 Digite a matrícula do usuário
4. 📚 Adicione ISBNs dos livros
5. ✅ Confirme o empréstimo
6. 📊 Visualize na tabela de empréstimos
```

### **🎯 Validações Inteligentes**
- ❌ **Campos obrigatórios** destacados em vermelho
- 📧 **Validação de email** em tempo real
- 📅 **Datas automáticas** baseadas no tipo de usuário
- 🔢 **ISBN único** verificado antes do cadastro
- ⚠️ **Limites de empréstimo** respeitados automaticamente

---

## 🎯 **Diferenciais da Versão Swing**

### **✨ Vantagens sobre Console**
| Aspecto | Console | **Swing** |
|---------|---------|-----------|
| **👀 Visualização** | Texto linear | 🎨 **Tabelas visuais organizadas** |
| **📊 Dados** | Lista sequencial | 📋 **Grid interativo com filtros** |  
| **🖱️ Interação** | Digitação apenas | 🎯 **Cliques, seleção, drag-drop** |
| **⚡ Velocidade** | Menu por menu | 🚀 **Acesso direto às funções** |
| **🎨 Experiência** | Básica | 💫 **Rica e profissional** |
| **📱 Usabilidade** | Técnica | 👥 **Amigável para todos** |

### **🚀 Features Exclusivas Swing**
- 🎨 **Interface visual moderna** com ícones e cores
- 📊 **Tabelas interativas** com ordenação por coluna
- 🔍 **Busca visual** com resultados instantâneos
- 📋 **Formulários validados** em tempo real
- 📈 **Dashboard de estatísticas** visual
- 🖱️ **Duplo-clique** para detalhes
- 💾 **Múltiplas ações** simultâneas
- ⚠️ **Alertas visuais** coloridos

---

## 🧪 **Como Testar o Sistema**

### **🎮 Cenários de Teste Recomendados**

#### **📚 Teste de Livros:**
```
1. ➕ Cadastre um livro com dados válidos
2. 🔍 Busque o livro por título
3. 📋 Visualize na tabela de livros
4. ✏️ Edite os dados (se implementado)
5. 🗑️ Teste exclusão (com validação)
```

#### **👥 Teste de Usuários:**
```
1. 🎓 Cadastre um aluno (limite: 3 livros)
2. 👨‍🏫 Cadastre um professor (limite: 5 livros)  
3. 📊 Verifique diferenças nos privilégios
4. 📋 Visualize na lista de usuários
```

#### **📋 Teste de Empréstimos:**
```
1. 📤 Realize empréstimo para aluno (2 livros)
2. 📊 Verifique atualização na tabela
3. ⏰ Observe as datas calculadas
4. 📥 Faça devolução usando número do empréstimo
5. ✅ Confirme liberação dos livros
```

#### **📊 Teste de Relatórios:**
```
1. 📈 Acesse aba de relatórios
2. 📊 Verifique estatísticas em tempo real
3. ⚠️ Visualize empréstimos atrasados
4. 🔄 Teste atualização de dados
```

---

## 🎓 **Valor Educacional**

### **🏆 Conceitos Demonstrados**
- **🖼️ GUI Programming** com Java Swing
- **🎨 Event-Driven Architecture** 
- **📊 Data Binding** entre model e view
- **🔄 MVC Pattern** em aplicações desktop
- **🧵 Thread Management** em interfaces gráficas
- **📱 UX/UI Design** para aplicações Java
- **🛡️ Input Validation** e tratamento de erros
- **📊 Data Visualization** com componentes nativos

### **💼 Aplicações Profissionais**
- **🏢 Sistemas Desktop** corporativos
- **📊 Dashboards** de gestão
- **🔧 Ferramentas** administrativas
- **📈 Aplicações** de relatórios
- **🎯 Protótipos** rápidos de interface

---

## 🔄 **Roadmap de Evolução**

### **🚀 Próximas Versões**
- **💾 Persistência** com banco de dados
- **🔐 Sistema de login** e autenticação
- **📊 Gráficos** com JFreeChart
- **📱 Look and Feel** customizado
- **🖨️ Exportação** de relatórios (PDF)
- **🔍 Busca avançada** com filtros múltiplos
- **📸 Upload** de capas de livros
- **🌐 Integração** com APIs externas

### **🏗️ Migrações Possíveis**
- **🌐 JavaFX** para interface mais moderna
- **☕ Spring Boot** para web application
- **📱 Android** para versão mobile
- **🌍 Web** com Vaadin ou JSF

---

## 🤝 **Contribuição**

Quer melhorar a interface gráfica? Contribuições são muito bem-vindas!

### **🎯 Áreas para Contribuição**
- 🎨 **Melhorias visuais** e UX
- 🔧 **Novos componentes** Swing
- 📊 **Relatórios visuais** avançados
- 🛡️ **Validações** mais robustas
- 📱 **Acessibilidade** e internacionalização
- 🧪 **Testes automatizados** da GUI

### **📝 Como Contribuir**
1. 🍴 **Fork** o repositório
2. 🌿 **Crie branch** para sua feature (`git checkout -b feature/SwingImprovement`)
3. 🎨 **Desenvolva** as melhorias visuais
4. 🧪 **Teste** a interface em diferentes sistemas
5. 💾 **Commit** com descrição clara (`git commit -m 'Add: Nova funcionalidade visual'`)
6. 📤 **Push** para sua branch (`git push origin feature/SwingImprovement`)
7. 🔄 **Abra Pull Request** detalhado

---

## 👨‍💻 **Autor**

Desenvolvido com ❤️ para demonstrar o poder das **interfaces gráficas em Java** e as melhores práticas de **desenvolvimento desktop**.

**📧 Contato:** [davi.armando@hotmail.com](mailto:davi.armando@hotmail.com)  
**🐙 GitHub:** [@DaviSales1308]([https://github.com/seu-usuario](https://github.com/DaviSales1308))  
**💼 LinkedIn:** [Davi Armando Lira Sales](https://linkedin.com/in/daavisales)

---
