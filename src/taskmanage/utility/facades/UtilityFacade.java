package taskmanage.utility.facades;

import taskmanage.utility.impl.DatabaseConnector;
import taskmanage.utility.impl.DataValidator;
import taskmanage.utility.impl.SecurityManager;
import taskmanage.model.impl.User;

import java.sql.Connection;
import java.sql.ResultSet;

public class UtilityFacade {
    private final SecurityManager securityManager;
    private final DatabaseConnector dbConnector;

    public UtilityFacade() {
        this.securityManager = new SecurityManager();
        this.dbConnector = DatabaseConnector.getInstance(); // Using singleton instance
    }

    // Database methods
    public Connection connectToDatabase() {
        return dbConnector.getConnection(); // Use getConnection to obtain the connection
    }

    public void executeUpdate(String query) {
        dbConnector.executeUpdate(query);
    }

    public ResultSet executeQuery(String query) {
        return dbConnector.executeQuery(query);
    }

    public void addUser(User user) {
        dbConnector.addUser(user);
    }

    public void closeConnection() {
        dbConnector.closeConnection();
    }

    public void backupData(String backupFilePath) {
        dbConnector.backupData(backupFilePath);
    }

    // Validation methods
    public boolean validateData(String data) {
        return DataValidator.validateString(data);
    }

    public boolean validateDate(String date) {
        return DataValidator.validateDate(date);
    }

    public boolean validatePriority(String priority) {
        return DataValidator.validatePriority(priority);
    }

    public boolean validateStatus(String status) {
        return DataValidator.validateStatus(status);
    }

    public boolean validateTag(String tag) {
        return DataValidator.validateTag(tag);
    }

    // Security methods
    public String hashPassword(String password) {
        return securityManager.hashPassword(password);
    }

    public byte[] generateSalt() {
        return securityManager.generateSalt();
    }

    public String hashPasswordWithSalt(String password, byte[] salt) {
        return securityManager.hashPasswordWithSalt(password, salt);
    }

    public boolean verifyPassword(String password, String hashedPassword, byte[] salt) {
        return securityManager.verifyPassword(password, hashedPassword, salt);
    }
}

