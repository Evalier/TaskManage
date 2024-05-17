package taskmanage.utility;
public class SecurityManager {
    // Method to authenticate users
    public static boolean authenticate(String username, String password) {
        // Authentication logic
        System.out.println("User authenticated");
        return true; // Example authentication
    }

    // Method to encrypt data
    public static String encrypt(String data) {
        // Encryption logic
        return data; // Example simple encryption
    }
}
