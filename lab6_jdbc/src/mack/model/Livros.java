package mack.model;

public class Livros {
    private Long id;
    private String nome;
    private String autor;

    public Livros(){}

    public Livros(Long id, String nome, String autor){
        this.id=id;
        this.nome=nome;
        this.autor=autor;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public void setAutor(String autor){
        this.autor=autor;
    }

    public Long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getAutor(){
        return this.autor;
    }
}