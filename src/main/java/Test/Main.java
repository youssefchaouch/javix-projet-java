package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static Connection con;
    private static String url="jdbc:mysql://localhost:3306/hr_management";
    private static String username="root";
    private static String password="1234";
    private static Statement stmt;
    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            stmt=con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
