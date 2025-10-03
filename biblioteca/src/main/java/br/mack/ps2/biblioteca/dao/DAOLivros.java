package br.mack.ps2.biblioteca;

import org.springframework.data.repository.CrudRepository;
import ps2.biblioteca.model.Livros;

public interface DAOLivros extends CrudRepository<Livros, Long> {}
