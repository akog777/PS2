package mack.principal;
import java.util.List;

import mack.dao.DAOLivros;
import mack.model.Livros;

public class App {
    public static void main(String[] args) throws Exception {
       Livros l = new Livros();
       l.setTitulo("Frankstein");
       l.setAutor("Mary Shelley");
       DAOLivros daoLivros = new DAOLivros();
       daoLivros.create(l);

       List<Livros> list = daoLivros.listAllLivros();
         System.out.println("Lista de livros:");

       for (Livros livro : list) {
            System.out.println(livro.getTitulo());
       }

    }
}