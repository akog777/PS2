package br.mackenzie.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mackenzie.usuario.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
