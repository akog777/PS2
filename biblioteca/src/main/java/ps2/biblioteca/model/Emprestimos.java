package ps2.biblioteca.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "emprestimos")
public class Emprestimos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_livro")
    private Long idLivro;

    @Column(name = "data_retirada")
    private LocalDate dataRetirada;

    public Emprestimos() {}

    public Emprestimos(Long id, Long idLivro, LocalDate dataRetirada) {
        this.id = id;
        this.idLivro = idLivro;
        this.dataRetirada = dataRetirada;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLivro() {
        return idLivro;
    }
    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }
    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }
}
