package session3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static  Connection getConnection() throws SQLException{
        Connection conn = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/students";
            conn = DriverManager.getConnection(dbURL,"root","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        if(getConnection()!=null){
            System.out.println("Connected");
        }
    }
}
