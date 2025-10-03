package br.mack.ps2.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import javax.swing.Spring;

import br.mack.ps2.biblioteca.dao.DAOLivros;
import br.mack.ps2.biblioteca.dao.DAOEmprestimos;
import br.mack.ps2.biblioteca.model.Livros;
import br.mack.ps2.biblioteca.model.Emprestimos;
import java.util.Scanner;


@SpringBootApplication
public class BibliotecaAppApplication {

	@autowired
	private LivrosDAO dao;
	@autowired
	private EmprestimosDAO daoE;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaAppApplication.class, args);
	}

	public void run(String... args) throws Exception{
		boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1- Listar todos ");
            System.out.println("2- Buscar por dados especificos");
            System.out.println("3- Criar um novo");
            System.out.println("4- Alterar os dados");
            System.out.println("5- Apagar por ID");
            System.out.println("0- Sair");
            System.out.print("Escolha: ");
            String menu = scanner.nextLine();

            switch (menu) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "0":
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}

/*
	public void createClinica(){
		Clinica c = new Clinica("Clinica Corinthians campeao !!!!");
		clinicaRepo.save(c);
	}

	public void createPaciente(){

		Paciente p = new Paciente( "joao");
		pacienteRepo.save(p);

		p = new Paciente( "maria");
		pacienteRepo.save(p);
	}

	public void listAllPacientes(){
		Iterable<Paciente> lista = pacienteRepo.findAll();

		for (Paciente paciente : lista) {
			System.out.println(paciente.getNome());
		}
	}

	public void cadastraPaciente(){
		Iterable<Paciente> lista = pacienteRepo.findAll();
		Clinica c = clinicaRepo.findById(1L).get();

		for (Paciente paciente : lista) {
			c.addPaciente(paciente);
		}
		clinicaRepo.save(c);
	}

	public void listaPacientesClinica(Long clinicaId){
		Clinica c = clinicaRepo.findById(clinicaId).get();
		List<Paciente> pacientes = c.getPacientes();

		for (Paciente paciente : pacientes) {
			System.out.println("Pacientes da clinica: "+paciente.getNome());
		}
	} */
