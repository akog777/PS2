package br.mack.ps2.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import br.mack.ps2.biblioteca.DAOLivros;
import br.mack.ps2.biblioteca.DAOEmprestimos;
import br.mack.ps2.biblioteca.Livros;
import br.mack.ps2.biblioteca.Emprestimo;


@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {
    
    @Autowired
    private DAOLivros daoLivros;

    @Autowired
    private DAOEmprestimos daoEmprestimos;

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    // Método para criar um novo livro
    public void criarLivro() {
        String titulo = input("Título: ");
        String autor = input("Autor: ");
        Livros livro = new Livros(null, titulo, autor);
        daoLivros.save(livro);
        print("Livro criado com ID " + livro.getId());
    }

    // Método para criar um novo empréstimo
    public void criarEmprestimo() {
        String idLivro = input("ID do livro para empréstimo: ");
        Livros livro = daoLivros.findById(Long.parseLong(idLivro)).orElse(null);
        if (livro != null) {
            String nomeCliente = input("Nome do cliente: ");
            Emprestimo emprestimo = new Emprestimo(null, nomeCliente, livro);
            daoEmprestimos.save(emprestimo);
            print("Empréstimo criado com ID " + emprestimo.getId());
        } else {
            print("Livro não encontrado!");
        }
    }

    // Listar todos os livros e empréstimos
    public void listarTudo() {
        // Listar livros
        Iterable<Livros> listaLivros = daoLivros.findAll();
        for (Livros livro : listaLivros) {
            print(livro.getId() + " - " + livro.getTitulo() + " - " + livro.getAutor());
        }

        // Listar empréstimos
        Iterable<Emprestimo> listaEmprestimos = daoEmprestimos.findAll();
        for (Emprestimo emprestimo : listaEmprestimos) {
            print(emprestimo.getId() + " - " + emprestimo.getNomeCliente() + " - " + emprestimo.getLivro().getTitulo());
        }
    }

    // Buscar por Livro
    public void buscarLivro() {
        String idLivro = input("Digite o ID do livro: ");
        Livros livro = daoLivros.findById(Long.parseLong(idLivro)).orElse(null);
        if (livro != null) {
            print("Livro encontrado: " + livro.getId() + " - " + livro.getTitulo() + " - " + livro.getAutor());
        } else {
            print("Livro não encontrado!");
        }
    }

    // Buscar por Empréstimo
    public void buscarEmprestimo() {
        String idEmprestimo = input("Digite o ID do empréstimo: ");
        Emprestimo emprestimo = daoEmprestimos.findById(Long.parseLong(idEmprestimo)).orElse(null);
        if (emprestimo != null) {
            print("Empréstimo encontrado: " + emprestimo.getId() + " - " + emprestimo.getNomeCliente() + " - " + emprestimo.getLivro().getTitulo());
        } else {
            print("Empréstimo não encontrado!");
        }
    }

    // Atualizar dados de um livro
    public void atualizarLivro() {
        String idLivro = input("Digite o ID do livro a ser atualizado: ");
        Livros livro = daoLivros.findById(Long.parseLong(idLivro)).orElse(null);
        if (livro != null) {
            String novoTitulo = input("Novo título: ");
            String novoAutor = input("Novo autor: ");
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            daoLivros.save(livro);
            print("Livro atualizado com sucesso!");
        } else {
            print("Livro não encontrado!");
        }
    }

    // Atualizar dados de um empréstimo
    public void atualizarEmprestimo() {
        String idEmprestimo = input("Digite o ID do empréstimo a ser atualizado: ");
        Emprestimo emprestimo = daoEmprestimos.findById(Long.parseLong(idEmprestimo)).orElse(null);
        if (emprestimo != null) {
            String novoNomeCliente = input("Novo nome do cliente: ");
            emprestimo.setNomeCliente(novoNomeCliente);
            daoEmprestimos.save(emprestimo);
            print("Empréstimo atualizado com sucesso!");
        } else {
            print("Empréstimo não encontrado!");
        }
    }

    // Apagar livro por ID
    public void apagarLivro() {
        String idLivro = input("Digite o ID do livro a ser apagado: ");
        Livros livro = daoLivros.findById(Long.parseLong(idLivro)).orElse(null);
        if (livro != null) {
            daoLivros.delete(livro);
            print("Livro apagado com sucesso!");
        } else {
            print("Livro não encontrado!");
        }
    }

    // Apagar empréstimo por ID
    public void apagarEmprestimo() {
        String idEmprestimo = input("Digite o ID do empréstimo a ser apagado: ");
        Emprestimo emprestimo = daoEmprestimos.findById(Long.parseLong(idEmprestimo)).orElse(null);
        if (emprestimo != null) {
            daoEmprestimos.delete(emprestimo);
            print("Empréstimo apagado com sucesso!");
        } else {
            print("Empréstimo não encontrado!");
        }
    }

    // Método principal do menu
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bem-vindo à Biblioteca!");
        boolean sair = false;
        String menu = "\n(1) Listar todos os livros e empréstimos";
        menu += "\n(2) Buscar por Livro";
        menu += "\n(3) Buscar por Empréstimo";
        menu += "\n(4) Criar novo livro";
        menu += "\n(5) Criar novo empréstimo";
        menu += "\n(6) Atualizar dados de um livro";
        menu += "\n(7) Atualizar dados de um empréstimo";
        menu += "\n(8) Apagar livro por ID";
        menu += "\n(9) Apagar empréstimo por ID";
        menu += "\n(0) Sair \n";
        menu += "Escolha uma opção: ";

        while (!sair) {
            String op = input(menu);
            switch (op) {
                case "1":
                    listarTudo();
                    break;
                case "2":
                    buscarLivro();
                    break;
                case "3":
                    buscarEmprestimo();
                    break;
                case "4":
                    criarLivro();
                    break;
                case "5":
                    criarEmprestimo();
                    break;
                case "6":
                    atualizarLivro();
                    break;
                case "7":
                    atualizarEmprestimo();
                    break;
                case "8":
                    apagarLivro();
                    break;
                case "9":
                    apagarEmprestimo();
                    break;
                case "0":
                    sair = true;
                    break;
                default:
                    print("Opção inválida!");
            }
        }
    }
    
    // Métodos auxiliares para entrada e saída (exemplo simples, substitua conforme necessário)
    private String input(String prompt) {
        System.out.print(prompt);
        return new java.util.Scanner(System.in).nextLine();
    }

    private void print(String message) {
        System.out.println(message);
    }
}
