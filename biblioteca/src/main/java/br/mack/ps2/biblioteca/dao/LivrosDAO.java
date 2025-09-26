package ps2.biblioteca.dao;

import org.springframework.data.repository.CrudRepository;
import ps2.biblioteca.model.Livros;

public interface LivrosDAO extends CrudRepository<Livros, Long>{

}
