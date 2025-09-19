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
                System.out.println("Inserido com sucesso");
         } catch (Exception e) {
            e.printStackTrace();
        }
    }
}