package br.mackenzie.usuario.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import br.mackenzie.usuario.repository.UsuarioRepository;
import br.mackenzie.usuario.entitites.Usuario;

@RestController
public class UserController {
    @Autowired
    private UserRepository rep;


    //CREATE
    @PostMapping("/users")
    public User criarUsuario(@RequestBody User novoUsuario){
        if (novoUsuario.getUsername() == null || novoUsuario.getPassword() == null || 
            novoUsuario.getUsername().isEmpty() || novoUsuario.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username e password são obrigatórios");
            
        }
        return rep.save(novoUsuario);
    }

    //READ
    @GetMapping("/users")
    public List<User> lerUsuarios(){
        return rep.findAll();
    }

    @GetMapping("/users/{id}")
    public User lerUsuarioPorId(@PathVariable long id){
        Optional<User> optional = rep.findById(id);
        if(optional.isPresent()) return optional.get();


        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }

    //UPDATE
    @PutMapping("/users/{id}")
    public User atualizarUsuarioPeloId(@RequestBody User novosDados, @PathVariable long id){

        Optional<User> optional = rep.findById(id);
        if(optional.isPresent()){
            User u = optional.get();
            u.setUsername(novosDados.getUsername());
            u.setPassword(novosDados.getPassword());
            return rep.save(u);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }

    //DELETE
    @DeleteMapping("/users/{id}")
    public User apagarPeloId(@PathVariable long id){
        Optional<User> optional = rep.findById(id);
        if(optional.isPresent()) {
            User u = optional.get();
            rep.delete(u);
            return u;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }
}