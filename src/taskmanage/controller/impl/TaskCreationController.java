package taskmanage.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.model.impl.Task;
import taskmanage.utility.impl.DataValidator;
import taskmanage.utility.facades.UtilityFacade;
import javafx.event.ActionEvent;
import taskmanage.main.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TaskCreationController {
    @FXML private TextField taskNameField;
    @FXML private TextArea taskDescriptionField;
    @FXML private DatePicker dueDatePicker;
    @FXML private ComboBox<PriorityLevel> priorityComboBox;
    @FXML private TextField tagsField;

    @FXML private TextField subtask1Field;
    @FXML private TextField subtask2Field;
    @FXML private TextField subtask3Field;
    @FXML private TextField taskCategoryField;
    @FXML private TextField reminderField;

    private static UtilityFacade dbConnector;

    public TaskCreationController() {
        if (dbConnector == null) {
            dbConnector = new UtilityFacade();
        }
    }

    @FXML
    public void initialize() {
        priorityComboBox.getItems().setAll(PriorityLevel.values());
    }

    @FXML
    public void handleSaveTask() {
        String name = taskNameField.getText();
        String description = taskDescriptionField.getText();
        LocalDate dueDate = dueDatePicker.getValue();
        PriorityLevel priority = priorityComboBox.getValue();
        String[] tagsArray = tagsField.getText().split(",");
        HashSet<String> tags = new HashSet<>(Arrays.asList(tagsArray));

        String subtask1 = subtask1Field.getText();
        String subtask2 = subtask2Field.getText();
        String subtask3 = subtask3Field.getText();
        String taskCategory = taskCategoryField.getText();
        String reminders = reminderField.getText();

        // Validate inputs
        if (!DataValidator.validateString(name) ||
                !tags.stream().allMatch(DataValidator::validateTag) ||
                (priority != null && !DataValidator.validateString(priority.toString())) ||
                (dueDate != null && !DataValidator.validateDate(dueDate.toString()))) {
            showAlert(Alert.AlertType.ERROR, "Invalid input. Please check your data.");
            return;
        }

        // Create and add task
        Task task = new Task(name, description, dueDate != null ? dueDate.toString() : null, priority);
        for (String tag : tags) {
            task.addTag(tag.trim());
        }
        if (subtask1 != null && !subtask1.isEmpty()) task.addSubtask(subtask1);
        if (subtask2 != null && !subtask2.isEmpty()) task.addSubtask(subtask2);
        if (subtask3 != null && !subtask3.isEmpty()) task.addSubtask(subtask3);
        task.setCategory(taskCategory);
        task.setReminders(reminders);

        addTaskToDatabase(task);
        showAlert(Alert.AlertType.INFORMATION, "Task created successfully.");
        closeWindow();
    }

    // Overloaded method for backward compatibility
    public void handleSaveTask(String name, String description, String dueDate, PriorityLevel priority, List<String> tags) {
        handleSaveTask(name, description, dueDate, priority, tags, null, null, null);
    }

    public void handleSaveTask(String name, String description, String dueDate, PriorityLevel priority, List<String> tags, List<String> subtasks, String taskCategory, String reminders) {
        HashSet<String> tagSet = new HashSet<>(tags);

        // Validate inputs
        if (!DataValidator.validateString(name) ||
                !tagSet.stream().allMatch(DataValidator::validateTag) ||
                (priority != null && !DataValidator.validateString(priority.toString())) ||
                (dueDate != null && !DataValidator.validateDate(dueDate)) ||
                (subtasks != null && !subtasks.stream().allMatch(DataValidator::validateString))) {
            showAlert(Alert.AlertType.ERROR, "Invalid input. Please check your data.");
            return;
        }

        // Create and add task
        Task task = new Task(name, description, dueDate, priority);
        for (String tag : tagSet) {
            task.addTag(tag.trim());
        }
        if (subtasks != null) {
            for (String subtask : subtasks) {
                if (subtask != null && !subtask.isEmpty()) task.addSubtask(subtask);
            }
        }
        task.setCategory(taskCategory);
        task.setReminders(reminders);

        addTaskToDatabase(task);
        showAlert(Alert.AlertType.INFORMATION, "Task created successfully.");
        closeWindow();
    }

    private void addTaskToDatabase(Task task) {
        String query = "INSERT INTO tasks (name, description, dueDate, priority, category, reminders) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbConnector.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getDueDate());
            preparedStatement.setString(4, task.getPriority() != null ? task.getPriority().toString() : null);
            preparedStatement.setString(5, task.getCategory());
            preparedStatement.setString(6, task.getReminders());
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

    @FXML
    private void handleHomeDashboard(ActionEvent event) {
        try {
            Main.showHomeDashboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTaskView(ActionEvent event) {
        try {
            Main.showTaskView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCalendarView(ActionEvent event) {
        try {
            Main.showCalendarView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


