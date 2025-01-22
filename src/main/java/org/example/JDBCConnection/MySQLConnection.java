package org.example.JDBCConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();

        try (InputStream input = MySQLConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, we can't find your file");
                return null;
            }

            // Load properties from the file
            props.load(input);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            // Create and return the database connection
            return DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error while connecting to the database", e);
        }
    }
}

