package ps2.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livros {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;

    public Livros(){}

    public Livros(Long id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTitulo() {
        return this.titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getAutor() {
        return this.autor;
    }
}