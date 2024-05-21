package taskmanage.utility;

import taskmanage.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/taskmanager";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private Connection connection;

    // Constructor to establish a database connection
    public DatabaseConnector() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to execute an update (INSERT, UPDATE, DELETE)
    public void executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to execute a query (SELECT)
    public ResultSet executeQuery(String query) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
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
            System.out.println("User added: " + user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            System.out.println("Data backup completed");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
