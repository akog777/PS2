package br.mack.ps2.biblioteca;

import org.springframework.data.repository.CrudRepository;
import ps2.biblioteca.model.Emprestimos;

public interface DAOEmprestimos extends CrudRepository<Emprestimo, Long> {
}
