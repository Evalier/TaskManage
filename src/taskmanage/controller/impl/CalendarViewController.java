package taskmanage.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import taskmanage.constants.EnumsAndConstants;
import taskmanage.controller.interfaces.ControllerInterface;
import taskmanage.model.impl.Task;
import taskmanage.utility.impl.DatabaseConnector;
import taskmanage.utility.facades.UtilityFacade;

import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalendarViewController implements ControllerInterface {
    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, String> nameColumn;
    @FXML private TableColumn<Task, String> dueDateColumn;
    @FXML private TableColumn<Task, String> priorityColumn;
    @FXML private TableColumn<Task, String> statusColumn;
    @FXML private TableColumn<Task, String> tagsColumn;

    private static UtilityFacade dbConnector;

    public CalendarViewController() {
        if (dbConnector == null) {
            dbConnector = new UtilityFacade();
        }
    }

    @Override
    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));

        loadTasks();
    }

    @Override
    public void handleActionEvent(ActionEvent event) {
        // Default implementation
    }

    @FXML
    public void handleRefresh() {
        loadTasks();
    }

    private void loadTasks() {
        List<Task> tasks = fetchAllTasks();
        taskTable.getItems().setAll(tasks);
    }

    public List<Task> fetchAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Connection connection = dbConnector.connectToDatabase();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("dueDate"),
                        EnumsAndConstants.PriorityLevel.valueOf(resultSet.getString("priority"))
                );
                // Set additional fields if necessary
                task.setStatus(EnumsAndConstants.TaskStatus.valueOf(resultSet.getString("status")));
                task.setTags(Collections.singleton(resultSet.getString("tags")));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
