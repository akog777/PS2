package br.mackenzie.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mackenzie.usuario.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
  Admin findByUsername(String username);
}
