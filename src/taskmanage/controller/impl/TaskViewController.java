package taskmanage.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import taskmanage.constants.EnumsAndConstants;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.constants.EnumsAndConstants.TaskStatus;
import taskmanage.controller.interfaces.ControllerInterface;
import taskmanage.model.impl.Task;
import taskmanage.utility.facades.UtilityFacade;

import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskViewController implements ControllerInterface {
    @FXML private Label nameLabel;
    @FXML private TextArea descriptionArea;
    @FXML private Label dueDateLabel;
    @FXML private Label priorityLabel;
    @FXML private Label statusLabel;
    @FXML private Label tagsLabel;
    @FXML private TableView<Task> taskTable;

    private Task task;
    private static UtilityFacade dbConnector;

    public TaskViewController() {
        if (dbConnector == null) {
            dbConnector = new UtilityFacade();
        }
    }

    @Override
    @FXML
    public void initialize() {
        // Add any necessary initialization code
    }

    @Override
    public void handleActionEvent(ActionEvent event) {
        // Default implementation
    }

    public void setTask(Task task) {
        this.task = task;
        updateUI();
    }

    private void updateUI() {
        nameLabel.setText(task.getName());
        descriptionArea.setText(task.getDescription());
        dueDateLabel.setText(task.getDueDate());
        priorityLabel.setText(task.getPriority().toString());
        statusLabel.setText(task.getStatus() != null ? task.getStatus().toString() : "No Status");
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
                deleteTaskFromDatabase(task.getID());
                showAlert(Alert.AlertType.INFORMATION, "Task deleted successfully.");
                loadTasks();
                closeWindow();
            }
        });
    }

    private void loadTasks() {
        List<Task> tasks = fetchTasks();
        taskTable.getItems().clear();
        taskTable.getItems().addAll(tasks);
    }

    public void deleteTaskFromDatabase(int taskId) {
        String query = "DELETE FROM tasks WHERE id = ?";
        try (Connection connection = dbConnector.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRefresh() {
        loadTasks();
    }

    public List<Task> fetchTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Connection connection = dbConnector.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Task task = new Task();
                task.setID(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDueDate(resultSet.getString("dueDate"));
                task.setPriority(PriorityLevel.valueOf(resultSet.getString("priority")));

                // Handle potential null values
                String statusStr = resultSet.getString("status");
                if (statusStr != null) {
                    task.setStatus(TaskStatus.valueOf(statusStr));
                } else {
                    task.setStatus(TaskStatus.PENDING); // or any default value
                }

                String tagsStr = resultSet.getString("tags");
                if (tagsStr != null && !tagsStr.isEmpty()) {
                    Set<String> tags = new HashSet<>(List.of(tagsStr.split(",")));
                    task.setTags(tags);
                }

                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
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

