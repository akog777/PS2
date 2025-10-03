package br.mack.ps2.biblioteca.dao;

import org.springframework.data.repository.CrudRepository;
import ps2.biblioteca.model.Livros;

public interface DAOEmprestimos extends CrudRepository<Emprestimos, Long>{

}
