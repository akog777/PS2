package br.mackenzie.user.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.user.entitites.User;

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
