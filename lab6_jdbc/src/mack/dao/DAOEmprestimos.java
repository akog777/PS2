package mack.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mack.model.Emprestimos;

public class DAOEmprestimos extends DAO{

    public List<Emprestimos> listAllEmprestimos(){
        List<Emprestimos> listRet = new ArrayList<>();
        try {
            Statement stmt = super.connect().createStatement();

            //Consultando
            ResultSet rs = stmt.executeQuery("SELECT * FROM Emprestimos");

            while(rs.next()){
                Emprestimos emp = new Emprestimos();
                emp.setId(rs.getLong("ID"));
                emp.setIdLivro(rs.getInt("ID_LIVRO"));
                emp.setDataRetirada(rs.getDate("DATA_RETIRADA"));
                listRet.add(emp);
            }
         } catch (Exception e) {
            e.printStackTrace();
        }

        return listRet;
    }

    public void create(Emprestimos emp){
        try {
        //inserindo
            String sql_insert = "INSERT INTO Emprestimos (ID_LIVRO, DATA_RETIRADA) VALUES(?,?)";

            PreparedStatement pstmt = super.connect().prepareStatement(sql_insert);

            pstmt.setInt(1, emp.getIdLivro());
            pstmt.setDate(2, new java.sql.Date(emp.getDataRetirada().getTime()));

            int qte = pstmt.executeUpdate();
            if(qte >=1)
                System.out.println("Empréstimo criado.");
         } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public Emprestimos select(long id) {
            Emprestimos emp = null;
            try {
                String sql = "SELECT * FROM Emprestimos WHERE ID = ?";
                PreparedStatement pstmt = super.connect().prepareStatement(sql);
                pstmt.setLong(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    emp = new Emprestimos();
                    emp.setId(rs.getLong("ID"));
                    emp.setIdLivro(rs.getInt("ID_LIVRO"));
                    emp.setDataRetirada(rs.getDate("DATA_RETIRADA"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return emp;
        }

        public void update(Emprestimos emp) {
            try {
                String sql = "UPDATE Emprestimos SET ID_LIVRO = ?, DATA_RETIRADA = ? WHERE ID = ?";
                PreparedStatement pstmt = super.connect().prepareStatement(sql);
                pstmt.setInt(1, emp.getIdLivro());
                pstmt.setDate(2, new java.sql.Date(emp.getDataRetirada().getTime()));
                pstmt.setLong(3, emp.getId());
                int qte = pstmt.executeUpdate();
                if(qte >= 1)
                    System.out.println("Empréstimo atualizado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void delete(long id) {
            try {
                String sql = "DELETE FROM Emprestimos WHERE ID = ?";
                PreparedStatement pstmt = super.connect().prepareStatement(sql);
                pstmt.setLong(1, id);
                int qte = pstmt.executeUpdate();
                if(qte >= 1)
                    System.out.println("Empréstimo deletado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}