package br.mackenzie.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mackenzie.user.entitites.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
