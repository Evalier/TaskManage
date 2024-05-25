package taskmanage.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.model.impl.Task;
import taskmanage.utility.impl.DataValidator;
import taskmanage.utility.impl.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class TaskCreationController {
    @FXML private TextField taskNameField;
    @FXML private TextArea taskDescriptionField;
    @FXML private DatePicker dueDatePicker;
    @FXML private ComboBox<PriorityLevel> priorityComboBox;
    @FXML private TextField tagsField;

    private static DatabaseConnector dbConnector;

    public TaskCreationController() {
        if (dbConnector == null) {
            dbConnector = new DatabaseConnector();
        }
    }

    @FXML
    public void initialize() {
        priorityComboBox.getItems().setAll(PriorityLevel.values());
    }

    @FXML
    private void handleSaveTask() {
        String name = taskNameField.getText();
        String description = taskDescriptionField.getText();
        LocalDate dueDate = dueDatePicker.getValue();
        PriorityLevel priority = priorityComboBox.getValue();
        String[] tagsArray = tagsField.getText().split(",");
        HashSet<String> tags = new HashSet<>(Arrays.asList(tagsArray));

        // Validate inputs
        if (!DataValidator.validateString(name) ||
                !DataValidator.validateString(description) ||
                dueDate == null ||
                !tags.stream().allMatch(DataValidator::validateTag)) {
            showAlert(Alert.AlertType.ERROR, "Invalid input. Please check your data.");
            return;
        }

        // Create and add task
        Task task = new Task(name, description, dueDate.toString(), priority);
        for (String tag : tags) {
            task.addTag(tag.trim());
        }
        addTaskToDatabase(task);
        showAlert(Alert.AlertType.INFORMATION, "Task created successfully.");
        closeWindow();
    }

    private void addTaskToDatabase(Task task) {
        String query = "INSERT INTO tasks (name, description, dueDate, priority) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getDueDate());
            preparedStatement.setString(4, task.getPriority().toString());
            preparedStatement.executeUpdate();
            System.out.println("Task added: " + task.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) taskNameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
