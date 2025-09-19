package mack.principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import mack.dao.DAOLivros;
import mack.model.Livros;

public class App {
    public static void main(String[] args) throws Exception {
       Livros l = new Livros();
       l.setNome("O Senhor dos Anéis");
       l.setAutor("J.R.R. Tolkien");
       p.setCpf("88888888-88");
       DAOProprietario daoProp = new DAOProprietario();
       daoProp.create(p);

       List<Proprietario> list = daoProp.listAllProprietarios();

       for (Proprietario proprietario : list) {
            System.out.println(proprietario.getNome());
       }

    }
}