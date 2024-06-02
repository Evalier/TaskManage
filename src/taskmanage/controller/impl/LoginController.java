package taskmanage.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import taskmanage.main.Main;
import taskmanage.utility.facades.UtilityFacade;

import java.io.IOException;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private static UtilityFacade dbConnector;

    public LoginController() {
        if (dbConnector == null) {
            dbConnector = new UtilityFacade();
        }
    }

    @FXML
    public void handleLoginAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password)) {
            // Load the HomeDashboard screen after successful login
            try {
                Main.showHomeDashboard();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Show login error message
            showAlert(Alert.AlertType.ERROR, "Invalid username or password.");
        }
    }

    private boolean authenticate(String username, String password) {
        // Replace with your actual authentication logic
        return "admin".equals(username) && "password".equals(password);
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

