package br.mackenzie.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mackenzie.user.entitites.usuario;


public interface UsuarioRepository extends JpaRepository<usuario, Long> {

}