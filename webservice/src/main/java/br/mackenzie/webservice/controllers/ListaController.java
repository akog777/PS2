package br.mackenzie.webservice.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.webservice.entities.Pessoa;

@RestController
@RequestMapping("/pessoas")
public class ListaController {
    private List<Pessoa> pessoas;
    private int idCount = 0;

    ListaController() {
        pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(idCount++, "Ana", 30));
        pessoas.add(new Pessoa(idCount++, "Bia", 25));
    }
    //CREATE
    @GetMapping()
    public List<Pessoa> todasAsPessoas() {
        return pessoas;
    }
    @GetMapping("/{id}")
    public Pessoa lerPorID(@PathVariable int id) {
        for (int i=0; i<pessoas.size(); i++) {
            if (pessoas.get(i).getId() == id) {
                return pessoas.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
    }

    //READ
    @PostMapping()
    public Pessoa adicionarPessoa(@RequestBody(required = true) Pessoa p) {
        p.setId(idCount++);
        pessoas.add(p);
        return p;
    }  

    //UPDATE
    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable int id, @RequestBody(required = true) Pessoa p) {
        if(id != p.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IDs diferentes");
        }
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa aux = pessoas.get(i);
            if(aux.getId() == id){
                pessoas.remove(aux);
                pessoas.add(p);
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");

    }

    //DELETE
    @DeleteMapping("/{id}")
    public Pessoa apagar(@PathVariable int id) {
        Pessoa p = null;
        for (Pessoa aux: pessoas){
            if(aux.getId() == id){
                p = aux;
            }
        }
        pessoas.remove(p);
        return p;
    }
}
