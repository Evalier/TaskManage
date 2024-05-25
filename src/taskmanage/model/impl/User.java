package taskmanage.model.impl;

import taskmanage.utility.facades.UtilityFacade;
import taskmanage.utility.impl.DatabaseConnector;
import taskmanage.utility.impl.SecurityManager;
import taskmanage.model.interfaces.ModelInterface;
import taskmanage.utility.facades.UtilityFacade;

import java.util.Arrays;

public class User implements ModelInterface {
    private String username;
    private String hashedPassword;
    private byte[] salt;

    // Constructor
    public User(String username, String password, byte[] salt, UtilityFacade securityManager) {
        this.username = username;
        this.salt = salt;
        this.hashedPassword = securityManager.hashPasswordWithSalt(username, password, salt);
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public boolean validate() {
        // Basic validation logic
        return true; // Replace with actual validation logic for your class
    }

    @Override
    public void save() {
        if (!validate()) {
            System.out.println("Validation failed. Cannot save.");
            return;
        }

        UtilityFacade dbConnector = new UtilityFacade();
        String query = "INSERT INTO tablename (fields) VALUES (values)"; // Replace with actual table name and fields
        dbConnector.executeUpdate(query);
        System.out.println("Record saved.");
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}

