package org.example.DAO;

import java.sql.*;
import org.example.Information.AdminInfo;
import org.example.JDBCConnection.MySQLConnection;

public class AdminDAO {

    public void addAdmin(AdminInfo admin) throws SQLException {
        String query = "INSERT INTO admin_info (name, surname, number, password) VALUES (?, ?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, admin.getName());
            statement.setString(2, admin.getSurname());
            statement.setString(3, admin.getNumber());
            statement.setInt(4, admin.getPassword());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin added successfully");
            } else {
                System.out.println("Failed to add new admin");
            }

        } catch (SQLException e) {

            e.printStackTrace();
            throw new SQLException("Error adding admin", e);
        }
    }
}
