package taskmanage.model;
import taskmanage.utility.SecurityManager;

import java.util.Arrays;

public class User {
    private String username;
    private String hashedPassword;
    private byte[] salt;

    // Constructor
    public User(String username, String password, byte[] salt, SecurityManager securityManager) {
        this.username = username;
        this.salt = salt;
        this.hashedPassword = securityManager.hashPasswordWithSalt(password, salt);
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
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}

