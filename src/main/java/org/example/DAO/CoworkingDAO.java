package org.example.DAO;

import java.sql.*;
import org.example.Information.CoworkingPlace;
import org.example.JDBCConnection.MySQLConnection;

public class CoworkingDAO {

    public void addCoworkingPlace(CoworkingPlace place) throws SQLException {
        String query = "INSERT INTO coworking_place (id, location, is_available) VALUES (?, ?, ?)";

        try (Connection connection = MySQLConnection.getConnection();  // Use MySQLConnection here
             PreparedStatement statement = connection.prepareStatement(query)) {


            statement.setInt(1, place.getId());
            statement.setString(2, place.getName());
            statement.setBoolean(3, place.isAvailable());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Coworking place added successfully.");
            } else {
                System.out.println("Failed to add coworking place.");
            }
        } catch (SQLException e) {
            // Handle exception and print the error
            e.printStackTrace();
            throw new SQLException("Error while adding coworking place", e);
        }
    }
}

