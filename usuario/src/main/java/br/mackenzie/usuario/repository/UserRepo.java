package br.mackenzie.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mackenzie.usuario.entities.User;


public interface UserRepo extends JpaRepository<User, Long> {

}