package mack.principal;

import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

import mack.dao.DAOLivros;
import mack.dao.DAOEmprestimos;
import mack.model.Livros;
import mack.model.Emprestimos;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        DAOLivros daoLivros = new DAOLivros();
        DAOEmprestimos daoEmprestimos = new DAOEmprestimos();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1 - Gerenciar Livros");
            System.out.println("2 - Gerenciar Empréstimos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            String menu = scanner.nextLine();

            switch (menu) {
                case "1":
                    // Lista de livros
                    List<Livros> livros = daoLivros.listAllLivros();
                    System.out.println("\n--- Livros cadastrados ---");
                    for (Livros l : livros) {
                        System.out.println(l.getId() + " - " + l.getTitulo() + " (" + l.getAutor() + ")");
                    }
                    // Menu CRUD
                    System.out.println("\n(a) Alterar | (d) Deletar | (n) Novo | (v) Voltar");
                    String opcaoLivro = scanner.nextLine();

                    if (opcaoLivro.equalsIgnoreCase("a")) {
                        System.out.print("ID do livro: ");
                        long id = Long.parseLong(scanner.nextLine());
                        Livros livro = daoLivros.select(id);
                        System.out.print("Novo título: ");
                        livro.setTitulo(scanner.nextLine());
                        System.out.print("Novo autor: ");
                        livro.setAutor(scanner.nextLine());
                        daoLivros.update(livro);
                    } 
                    else if (opcaoLivro.equalsIgnoreCase("d")) {
                        System.out.print("ID do livro: ");
                        daoLivros.delete(Long.parseLong(scanner.nextLine()));
                    } 
                    else if (opcaoLivro.equalsIgnoreCase("n")) {
                        Livros novo = new Livros();
                        System.out.print("Título: ");
                        novo.setTitulo(scanner.nextLine());
                        System.out.print("Autor: ");
                        novo.setAutor(scanner.nextLine());
                        daoLivros.create(novo);
                    }
                    break;

                case "2":
                    // Lista de empréstimos
                    List<Emprestimos> emprestimos = daoEmprestimos.listAllEmprestimos();
                    System.out.println("\n--- Empréstimos cadastrados ---");
                    for (Emprestimos e : emprestimos) {
                        System.out.println(e.getId() + " - Livro: " + e.getIdLivro() + " | Data retirada: " + sdf.format(e.getDataRetirada()));
                    }
                    // Menu CRUD
                    System.out.println("\n(a) Alterar | (d) Deletar | (n) Novo | (v) Voltar");
                    String opcaoEmp = scanner.nextLine();

                    if (opcaoEmp.equalsIgnoreCase("a")) {
                        System.out.print("ID do empréstimo: ");
                        long id = Long.parseLong(scanner.nextLine());
                        Emprestimos emp = daoEmprestimos.select(id);
                        System.out.print("Novo ID do livro: ");
                        emp.setIdLivro(Integer.parseInt(scanner.nextLine()));
                        System.out.print("Nova data (yyyy-MM-dd): ");
                        emp.setDataRetirada(sdf.parse(scanner.nextLine()));
                        daoEmprestimos.update(emp);
                    } 
                    else if (opcaoEmp.equalsIgnoreCase("d")) {
                        System.out.print("ID do empréstimo: ");
                        daoEmprestimos.delete(Long.parseLong(scanner.nextLine()));
                    } 
                    else if (opcaoEmp.equalsIgnoreCase("n")) {
                        Emprestimos novo = new Emprestimos();
                        System.out.print("ID do livro: ");
                        novo.setIdLivro(Integer.parseInt(scanner.nextLine()));
                        System.out.print("Data de retirada (yyyy-MM-dd): ");
                        novo.setDataRetirada(sdf.parse(scanner.nextLine()));
                        daoEmprestimos.create(novo);
                    }
                    break;

                case "0":
                    rodando = false;
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
