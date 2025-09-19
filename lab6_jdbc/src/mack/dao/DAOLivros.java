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
            ResultSet rs = stmt.executeQuery("SELECT * FROM livros");

            while(rs.next()){
                Livros liv = new Livros();
                liv.setId(rs.getLong("ID"));
                liv.setTitulo(rs.getString("titulo"));
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
            String sql_insert = "INSERT INTO Livros (TITULO, AUTOR) VALUES(?,?)";

            PreparedStatement pstmt = super.connect().prepareStatement(sql_insert);

            pstmt.setString(1, liv.getTitulo());
            pstmt.setString(2, liv.getAutor());

            int qte = pstmt.executeUpdate();
            if(qte >=1)
                System.out.println("Livro criado.");
         } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public Livros select(long id) {
            Livros liv = null;
            try {
                String sql = "SELECT * FROM Livros WHERE ID = ?";
                PreparedStatement pstmt = super.connect().prepareStatement(sql);
                pstmt.setLong(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    liv = new Livros();
                    liv.setId(rs.getLong("ID"));
                    liv.setTitulo(rs.getString("TITULO"));
                    liv.setAutor(rs.getString("AUTOR"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return liv;
        }

        public void update(Livros liv) {
            try {
                String sql = "UPDATE Livros SET TITULO = ?, AUTOR = ? WHERE ID = ?";
                PreparedStatement pstmt = super.connect().prepareStatement(sql);
                pstmt.setString(1, liv.getTitulo());
                pstmt.setString(2, liv.getAutor());
                pstmt.setLong(3, liv.getId());
                int qte = pstmt.executeUpdate();
                if(qte >= 1)
                    System.out.println("Livro atualizado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void delete(long id) {
            try {
                String sql = "DELETE FROM Livros WHERE ID = ?";
                PreparedStatement pstmt = super.connect().prepareStatement(sql);
                pstmt.setLong(1, id);
                int qte = pstmt.executeUpdate();
                if(qte >= 1)
                    System.out.println("Livro deletado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}