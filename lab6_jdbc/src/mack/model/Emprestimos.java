package mack.model;

public class Emprestimos {
    private long id;
    private int idLivro;
    private java.util.Date dataRetirada;

    public Emprestimos(){}

    public Emprestimos(Long id, int idLivro, java.util.Date dataRetirada){
        this.id = id;
        this.idLivro = idLivro;
        this.dataRetirada = dataRetirada;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setIdLivro(int idLivro){
        this.idLivro=idLivro;
    }

    public void setDataRetirada(java.util.Date dataRetirada){
        this.dataRetirada = dataRetirada;
    }

    public Long getId(){
        return this.id;
    }

    public int getIdLivro(){
        return this.idLivro;
    }

    public java.util.Date getDataRetirada(){
        return this.dataRetirada;
    }
}