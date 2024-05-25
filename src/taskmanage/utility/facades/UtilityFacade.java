package taskmanage.utility.facades;

import taskmanage.utility.impl.DatabaseConnector;
import taskmanage.utility.impl.DataValidator;
import taskmanage.utility.impl.SecurityManager;
import java.sql.Connection;

public class UtilityFacade {

    private final SecurityManager securityManager;
    private final DatabaseConnector dbConnector;

    public UtilityFacade() {
        this.securityManager = new SecurityManager();
        this.dbConnector = new DatabaseConnector();
    }

    public Connection connectToDatabase() {
        return dbConnector.getConnection();
    }

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
