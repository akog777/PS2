package ps2.biblioteca;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ps2.biblioteca.dao.DAOEmprestimos;
import ps2.biblioteca.dao.DAOLivros;
import ps2.biblioteca.model.Emprestimos;
import ps2.biblioteca.model.Livros;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

    @Autowired
    private DAOLivros livrosDAO;
    @Autowired
    private DAOEmprestimos empDAO;

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }
    
    private String nextLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private Long nextLong(String prompt) {
        try {
            return Long.parseLong(nextLine(prompt));
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número longo (ID).");
            return null; 
        }
    }
    
    public void listar() {
        Iterable<Livros> livros = livrosDAO.findAll();
        Iterable<Emprestimos> emprestimos = empDAO.findAll();
        System.out.println("\n======= Livros =======");
        for (Livros l : livros) {
            System.out.println("ID: " + l.getId());
            System.out.println("Título: " + l.getTitulo());
            System.out.println("Autor: " + l.getAutor());
            System.out.println("=======================");
        }
        System.out.println("\n===== Empréstimos =====");
        for (Emprestimos e : emprestimos) {
            System.out.println("ID: " + e.getId());
            System.out.println("ID do Livro: " + e.getIdLivro());
            System.out.println("Data de Empréstimo: " + e.getDataRetirada());
            System.out.println("=======================");
        }
        System.out.println();
    }

    public void buscarEmprestimo() {
        Long id = nextLong("Digite o ID do empréstimo: ");
        if (id == null) return;

        Optional<Emprestimos> optionalEmprestimo = empDAO.findById(id);

        if (optionalEmprestimo.isPresent()) {
            Emprestimos e = optionalEmprestimo.get();
            System.out.println("ID: " + e.getId());
            System.out.println("ID do Livro: " + e.getIdLivro());
            System.out.println("Data de Empréstimo: " + e.getDataRetirada());
            System.out.println("-----------------------\n");
        } else {
            System.out.println("Empréstimo não encontrado!\n");
        }
    }
    
    public void buscarLivro() {
        Long id = nextLong("Digite o ID do livro: ");
        if (id == null) return;
        
        Optional<Livros> optionalLivro = livrosDAO.findById(id);

        if (optionalLivro.isPresent()) {
            Livros l = optionalLivro.get();
            System.out.println("ID: " + l.getId());
            System.out.println("Título: " + l.getTitulo());
            System.out.println("Autor: " + l.getAutor());
            System.out.println("-----------------------\n");
        } else {
            System.out.println("Livro não encontrado!\n");
        }
    }

    public void criarLivro() {
        String titulo = nextLine("Digite o título do livro: ");
        String autor = nextLine("Digite o autor do livro: ");
        Livros l = new Livros();
        l.setTitulo(titulo);
        l.setAutor(autor);
        livrosDAO.save(l);
        System.out.println("Livro criado com sucesso!\n");
    }

    public void criarEmprestimo() {
        Long livro_id = nextLong("Digite o ID do livro: ");
        if (livro_id == null) return;

        String data_emprestimo_str = nextLine("Digite a data de empréstimo (formato YYYY-MM-DD): ");
        
        Emprestimos e = new Emprestimos(); 
        e.setIdLivro(livro_id);
        
        try {
            LocalDate dataEmprestimo = LocalDate.parse(data_emprestimo_str); 
            e.setDataRetirada(dataEmprestimo);
            empDAO.save(e);
            System.out.println("Empréstimo criado com sucesso!\n");
        } catch (DateTimeParseException ex) {
            System.out.println("Erro: Formato de data inválido. Use YYYY-MM-DD. Empréstimo não criado.\n");
        }
    }

    public void alterarDadosLivro() {
        Long id = nextLong("Digite o ID do livro: ");
        if (id == null) return;

        Optional<Livros> optionalLivro = livrosDAO.findById(id);
        if (optionalLivro.isPresent()) {
            Livros l = optionalLivro.get();
            String titulo = nextLine("Digite o novo título do livro (atual: " + l.getTitulo() + "): ");
            String autor = nextLine("Digite o novo autor do livro (atual: " + l.getAutor() + "): ");
            
            if (!titulo.isBlank()) l.setTitulo(titulo);
            if (!autor.isBlank()) l.setAutor(autor);
            
            livrosDAO.save(l);
            System.out.println("Livro alterado com sucesso!\n");
        } else {
            System.out.println("Livro não encontrado!\n");
        }
    }
    
    public void alterarDadosEmprestimo() {
        Long id = nextLong("Digite o ID do empréstimo: ");
        if (id == null) return;

        Optional<Emprestimos> optionalEmprestimo = empDAO.findById(id);
        if (optionalEmprestimo.isPresent()) {
            Emprestimos e = optionalEmprestimo.get();
            
            String novoLivroIdStr = nextLine("Digite o novo ID do livro (atual: " + e.getIdLivro() + "). Deixe em branco para manter: ");
            if (!novoLivroIdStr.isBlank()) {
                 try {
                    Long novoLivroId = Long.parseLong(novoLivroIdStr);
                    e.setIdLivro(novoLivroId);
                } catch (NumberFormatException ex) {
                    System.out.println("Aviso: Formato do ID do livro inválido. O ID anterior será mantido.");
                }
            }
            
            String data_emprestimo_str = nextLine("Digite a nova data de empréstimo (atual: " + e.getDataRetirada() + "). Deixe em branco para manter: ");

            if (!data_emprestimo_str.isBlank()) {
                try {
                    e.setDataRetirada(LocalDate.parse(data_emprestimo_str));
                } catch (DateTimeParseException ex) {
                    System.out.println("Aviso: Formato de data inválido. Use YYYY-MM-DD. A data anterior será mantida.");
                }
            }

            empDAO.save(e);
            System.out.println("Empréstimo alterado com sucesso!\n");
        } else {
            System.out.println("Empréstimo não encontrado!\n");
        }
    }

    public void deletarEmprestimo() {
        Long id = nextLong("Digite o ID do empréstimo: ");
        if (id == null) return;

        if (empDAO.existsById(id)) {
            empDAO.deleteById(id);
            System.out.println("Empréstimo deletado com sucesso!\n");
        } else {
            System.out.println("Empréstimo não encontrado!\n");
        }
    }
    
    public void deletarLivro() {
        Long id = nextLong("Digite o ID do livro: ");
        if (id == null) return;

        if (livrosDAO.existsById(id)) {
            livrosDAO.deleteById(id);
            System.out.println("Livro deletado com sucesso!\n");
        } else {
            System.out.println("Livro não encontrado!\n");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        boolean saida = false;

        while (!saida) {
            System.out.println("\n===== Biblioteca Mack =====");
            System.out.println("1 - Listar todos livros e empréstimos");
            System.out.println("2 - Buscar Livro por ID");
            System.out.println("3 - Buscar Empréstimo por ID");
            System.out.println("4 - Criar novo Livro");
            System.out.println("5 - Criar novo Empréstimo");
            System.out.println("6 - Alterar dados de um Livro");
            System.out.println("7 - Alterar dados de um Empréstimo");
            System.out.println("8 - Apagar um Livro por ID");
            System.out.println("9 - Apagar um Empréstimo por ID");
            System.out.println("0 - Sair");
            System.out.println();

            String opStr = nextLine("Digite a opção desejada: ");
            int opcao;
            try {
                opcao = Integer.parseInt(opStr);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    buscarLivro(); 
                    break;
                case 3:
                    buscarEmprestimo(); 
                    break;
                case 4:
                    criarLivro();
                    break;
                case 5:
                    criarEmprestimo();
                    break;
                case 6:
                    alterarDadosLivro();
                    break;
                case 7:
                    alterarDadosEmprestimo();
                    break;
                case 8:
                    deletarLivro();
                    break;
                case 9:
                    deletarEmprestimo();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    saida = true;
                    scanner.close(); 
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
