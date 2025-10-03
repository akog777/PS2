package br.mack.ps2.biblioteca.model;

import jakarta.persistence.*;

@Entity
public class Livros {
    @Id @GeneratedValue
    private Long id;
    private String titulo;
    private String autor;

    public Livros(){}

    public Livros(Long id, String titulo, String autor){
        this.id=id;
        this.titulo=titulo;
        this.autor=autor;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setTitulo(String titulo){
        this.titulo=titulo;
    }

    public void setAutor(String autor){
        this.autor=autor;
    }

    public Long getId(){
        return this.id;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getAutor(){
        return this.autor;
    }
}