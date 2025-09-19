package mack.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mack.model.Livros;

public class DAOLivros extends DAO{

    public List<Livros> listAllLivros(){
        List<Livros> listRet = new ArrayList<>();
        try {
            Statement stmt = super.connect().createStatement();

            //Consultando
            ResultSet rs = stmt.executeQuery("SELECT * FROM Livros");

            while(rs.next()){
                Livros liv = new Livros();
                liv.setId(rs.getLong("ID"));
                liv.setNome(rs.getString("nome"));
                liv.setAutor(rs.getString("autor"));
                listRet.add(liv);
            }
         } catch (Exception e) {
            e.printStackTrace();
        }

        return listRet;
    }

    public void create(Livros liv){
        try {
        //inserindo
            String sql_insert = "INSERT INTO Livros (NOME, AUTOR) VALUES(?,?)";

            PreparedStatement pstmt = super.connect().prepareStatement(sql_insert);

            pstmt.setString(1, liv.getNome());
            pstmt.setString(2, liv.getAutor());

            int qte = pstmt.executeUpdate();
            if(qte >=1)
                System.out.println("inserido com sucesso");
         } catch (Exception e) {
            e.printStackTrace();
        }
    }
}