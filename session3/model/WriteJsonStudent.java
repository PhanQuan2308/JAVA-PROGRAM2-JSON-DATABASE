package session3.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import session3.dao.DbConnection;
import session3.entity.Students;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WriteJsonStudent {
    private static Connection conn;

    static {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeStudent() {
        List<Students> students = new ArrayList<>();
        try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM Student")) {
            while (resultSet.next()) {
                Students student = new Students();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAddress(resultSet.getString("address"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));
                student.setDate(resultSet.getDate("DOB")); // Use the correct column name
                students.add(student);
            }

            // Update Gson instance with custom date format
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd-MM-yyyy")
                    .setPrettyPrinting()
                    .create();

            try (FileWriter writer = new FileWriter("student.json")) {
                gson.toJson(students, writer);
            }
            System.out.println("Data written to student.json");

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
