import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.gcodhdlriexrjvodvily&password=bnVIWrSHKmqoqK6x";
            Connection conn = DriverManager.getConnection(url);

            while (true) {
                System.out.println("\n ============ Menu:============");
                System.out.println("| 1 - Listar livros            |");
                System.out.println("| 2 - Inserir livro            |");
                System.out.println("| 3 - Atualizar autor de livro |");
                System.out.println("| 4 - Remover livro            |");
                System.out.println("| 0 - Sair                     |");
                System.out.println(" ==============================");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 0) {
                    System.out.println("Saindo...");
                    break;
                }

                switch (opcao) {
                    case 1:
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM LIVROS");
                        System.out.println("\nLista de livros:");
                        while (rs.next()) {
                            System.out.println("ID: " + rs.getString("ID"));
                            System.out.println("TITULO: " + rs.getString("TITULO"));
                            System.out.println("AUTOR: " + rs.getString("AUTOR"));
                        }
                        rs.close();
                        st.close();
                        break;
                    case 2:
                        System.out.print("Digite o título do livro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Digite o autor do livro: ");
                        String autor = scanner.nextLine();
                        String sql_insert = "INSERT INTO LIVROS (TITULO, AUTOR) VALUES (?,?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql_insert);
                        pstmt.setString(1, titulo);
                        pstmt.setString(2, autor);
                        int qte = pstmt.executeUpdate();
                        if (qte >= 1) {
                            System.out.println("Livro inserido com sucesso!");
                        }
                        pstmt.close();
                        break;
                    case 3:
                        System.out.print("Digite o título do livro a atualizar: ");
                        String tituloUpdate = scanner.nextLine();
                        System.out.print("Digite o novo autor: ");
                        String autorUpdate = scanner.nextLine();
                        String sql_update = "UPDATE LIVROS SET AUTOR = ? WHERE TITULO = ?";
                        PreparedStatement pstmtUpdate = conn.prepareStatement(sql_update);
                        pstmtUpdate.setString(1, autorUpdate);
                        pstmtUpdate.setString(2, tituloUpdate);
                        int updated = pstmtUpdate.executeUpdate();
                        if (updated >= 1) {
                            System.out.println("Livro atualizado com sucesso!");
                        } else {
                            System.out.println("Livro não encontrado!");
                        }
                        pstmtUpdate.close();
                        break;
                    case 4:
                        System.out.print("Digite o título do livro a remover: ");
                        String tituloDelete = scanner.nextLine();
                        String sql_delete = "DELETE FROM LIVROS WHERE TITULO = ?";
                        PreparedStatement pstmtDelete = conn.prepareStatement(sql_delete);
                        pstmtDelete.setString(1, tituloDelete);
                        int deleted = pstmtDelete.executeUpdate();
                        if (deleted >= 1) {
                            System.out.println("Livro removido com sucesso!");
                        } else {
                            System.out.println("Livro não encontrado!");
                        }
                        pstmtDelete.close();
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            conn.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}