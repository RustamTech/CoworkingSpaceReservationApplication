package org.example.DAO;
import java.util.*;
import java.sql.*;
import org.example.Information.UserInfo;
import org.example.JDBCConnection.MySQLConnection;

public class UserDAO {

    public void addUser(UserInfo user) throws SQLException {
        String query = "INSERT INTO user_info (name, surname, password) VALUES (?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getPassword());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }
        }
    }
}