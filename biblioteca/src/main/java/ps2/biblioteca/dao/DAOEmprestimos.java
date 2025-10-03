package ps2.biblioteca.dao;

import org.springframework.data.repository.CrudRepository;
import ps2.biblioteca.model.Emprestimos;
import java.util.List;

public interface DAOEmprestimos extends CrudRepository<Emprestimos, Long> {
    List<Emprestimos> findByIdLivro(Long idLivro);
}
