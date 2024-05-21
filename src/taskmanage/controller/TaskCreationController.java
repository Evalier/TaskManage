package taskmanage.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.model.Task;
import taskmanage.utility.DataValidator;
import taskmanage.controller.TaskController;

import java.util.Arrays;
import java.util.HashSet;

public class TaskCreationController {
    @FXML private TextField nameField;
    @FXML private TextArea descriptionArea;
    @FXML private TextField dueDateField;
    @FXML private ComboBox<PriorityLevel> priorityComboBox;
    @FXML private TextField tagsField;

    private TaskController taskController;

    public TaskCreationController() {
        taskController = new TaskController();
    }

    @FXML
    public void initialize() {
        priorityComboBox.getItems().setAll(PriorityLevel.values());
    }

    @FXML
    private void handleCreateTask() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        String dueDate = dueDateField.getText();
        PriorityLevel priority = priorityComboBox.getValue();
        String[] tagsArray = tagsField.getText().split(",");
        HashSet<String> tags = new HashSet<>(Arrays.asList(tagsArray));

        // Validate inputs
        if (!DataValidator.validateString(name) ||
                !DataValidator.validateString(description) ||
                !DataValidator.validateDate(dueDate) ||
                !tags.stream().allMatch(DataValidator::validateTag)) {
            showAlert(Alert.AlertType.ERROR, "Invalid input. Please check your data.");
            return;
        }

        // Create and add task
        Task task = new Task(name, description, dueDate, priority);
        for (String tag : tags) {
            task.addTag(tag.trim());
        }
        taskController.addTask(task);
        showAlert(Alert.AlertType.INFORMATION, "Task created successfully.");
        closeWindow();
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
