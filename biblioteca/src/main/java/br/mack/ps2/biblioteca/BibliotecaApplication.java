package ps2.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootApplication
public class BibliotecaAppApplication {

	@autowired
	private LivrosDAO livrosDAO;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	public void run(String... args) throws Exception{
		Livros livro1 = new Livros(null, "O Senhor dos Aneis", "J.R.R. Tolkien");
		Livros livro2 = new Livros(null, "1984", "George Orwell");
		Livros livro3 = new Livros(null, "O Pequeno Principe", "Antoine de Saint-Exupery");

		this.livrosDAO.save(livro1);
		this.livrosDAO.save(livro2);
		this.livrosDAO.save(livro3);

		Iterable<Livros> livros = this.livrosDAO.findAll();
		for(Livros livro: livros){
			System.out.println(livro.getId() + " - " + livro.getTitulo() + " - " + livro.getAutor());
		}
	}

}
