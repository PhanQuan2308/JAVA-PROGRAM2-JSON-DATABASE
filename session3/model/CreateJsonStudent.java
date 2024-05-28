package session3.model;

import session3.dao.DbConnection;
import session3.entity.Students;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CreateJsonStudent {
    private static final String INSERT_QUERY = "INSERT INTO Student (id, name, address, email, phone, DOB) VALUES (?, ?, ?, ?, ?, ?)";

    private Connection conn;

    public CreateJsonStudent() {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database: " + e.getMessage());
        }
    }

    public void addStudentsToDatabase(List<Students> students) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY)) {
            for (Students student : students) {
                preparedStatement.setInt(1, student.getId());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setString(3, student.getAddress());
                preparedStatement.setString(4, student.getEmail());
                preparedStatement.setString(5, student.getPhone());
                preparedStatement.setDate(6, new java.sql.Date(student.getDate().getTime()));  // Convert java.util.Date to java.sql.Date
                preparedStatement.executeUpdate();
            }
            System.out.println("Data inserted into database.");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting data into database: " + e.getMessage());
        }
    }
}
