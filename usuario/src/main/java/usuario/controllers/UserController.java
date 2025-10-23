package br.mackenzie.usuario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.usuario.entities.Admin;
import br.mackenzie.usuario.entities.User;
import br.mackenzie.usuario.repositories.AdminRepository;
import br.mackenzie.usuario.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AdminRepository adminRepository;

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
  public User createUser(@RequestBody User user, @RequestParam(required = false) String type) {
    if ("admin".equalsIgnoreCase(type)) {
      Admin admin = new Admin();
      admin.setUsername(user.getUsername());
      admin.setPassword(user.getPassword());
      admin.setDescription("Usuário administrador do sistema");
      return adminRepository.save(admin);
    }
    return userRepository.save(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
  }
}