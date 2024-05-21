package taskmanage.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import taskmanage.model.Task;

public class TaskViewController {
    @FXML private Label nameLabel;
    @FXML private TextArea descriptionArea;
    @FXML private Label dueDateLabel;
    @FXML private Label priorityLabel;
    @FXML private Label statusLabel;
    @FXML private Label tagsLabel;

    private Task task;

    public void setTask(Task task) {
        this.task = task;
        updateUI();
    }

    private void updateUI() {
        nameLabel.setText(task.getName());
        descriptionArea.setText(task.getDescription());
        dueDateLabel.setText(task.getDueDate());
        priorityLabel.setText(task.getPriority().toString());
        statusLabel.setText(task.getStatus().toString());
        tagsLabel.setText(String.join(", ", task.getTags()));
    }

    @FXML
    private void handleEdit() {
        showAlert(Alert.AlertType.INFORMATION, "Edit functionality is not implemented yet.");
    }

    @FXML
    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete this task?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Implement task deletion logic
                showAlert(Alert.AlertType.INFORMATION, "Delete functionality is not implemented yet.");
                closeWindow();
            }
        });
    }

    private void closeWindow() {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
