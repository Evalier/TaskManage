package taskmanage.utility.impl;

import taskmanage.model.impl.User;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/taskmanage";
    private static final String USER = "root";
    private static final String PASSWORD = "cs380";
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnector.class.getName());
    private Connection connection;

    // Constructor to establish a database connection
    public DatabaseConnector() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.log(Level.INFO, "Database connection established");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to establish database connection", e);
        }
    }

    // Method to return the database connection
    public Connection getConnection() {
        return connection;
    }

    // Method to execute an update (INSERT, UPDATE, DELETE)
    public void executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to execute update", e);
        }
    }

    // Method to execute a query (SELECT)
    public ResultSet executeQuery(String query) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to execute query", e);
        }
        return null;
    }

    // Method to add a user to the database
    public void addUser(User user) {
        String query = "INSERT INTO users (username, hashedPassword, salt) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getHashedPassword());
            preparedStatement.setBytes(3, user.getSalt());
            preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "User added: " + user.getUsername());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to add user", e);
        }
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to close database connection", e);
        }
    }

    // Method to backup data to a file
    public void backupData(String backupFilePath) {
        try (FileWriter writer = new FileWriter(backupFilePath)) {
            String query = "SELECT * FROM tasks";
            ResultSet resultSet = executeQuery(query);
            while (resultSet.next()) {
                String taskData = resultSet.getInt("id") + ", " +
                        resultSet.getString("name") + ", " +
                        resultSet.getString("description") + ", " +
                        resultSet.getString("dueDate") + ", " +
                        resultSet.getString("completionDate") + ", " +
                        resultSet.getString("priority") + ", " +
                        resultSet.getString("status") + "\n";
                writer.write(taskData);
            }
            LOGGER.log(Level.INFO, "Data backup completed");
        } catch (SQLException | IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to backup data", e);
        }
    }
}
