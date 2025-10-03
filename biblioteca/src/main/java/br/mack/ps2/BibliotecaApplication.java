package br.mack.ps2.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

import br.mack.ps2.biblioteca.dao.LivrosDAO;
import br.mack.ps2.biblioteca.model.Livros;


@SpringBootApplication
public class BibliotecaAppApplication {

	@autowired
	private LivrosDAO livrosDAO;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	public void run(String... args) throws Exception{
        
	}

}