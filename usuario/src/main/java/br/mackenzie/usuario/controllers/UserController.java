package br.mackenzie.usuario.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.usuario.entities.User;
import br.mackenzie.usuario.repositories.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping()
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
    }
    return user;
  }

  @PostMapping()
  public User createUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
  }
}
