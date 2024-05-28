package taskmanage.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import taskmanage.constants.EnumsAndConstants;
import taskmanage.controller.interfaces.ControllerInterface;
import taskmanage.model.impl.Task;
import taskmanage.utility.facades.UtilityFacade;
import taskmanage.main.Main;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalendarViewController implements ControllerInterface {
    @FXML private Button btnPreviousMonth;
    @FXML private Button btnNextMonth;
    @FXML private Label lblMonthYear;
    @FXML private Pane day1;
    @FXML private Pane day2;
    @FXML private TextArea txtDayDetails;
    @FXML private Button btnEditTask;
    @FXML private Button btnDeleteTask;

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

    @FXML
    private void handlePreviousMonth() {
        // Logic to handle previous month action
        System.out.println("Previous month button clicked");
    }

    @FXML
    private void handleNextMonth() {
        // Logic to handle next month action
        System.out.println("Next month button clicked");
    }

    @FXML
    private void handleEditTask() {
        // Logic to handle edit task action
        System.out.println("Edit task button clicked");
    }

    @FXML
    private void handleDeleteTask() {
        // Logic to handle delete task action
        System.out.println("Delete task button clicked");
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

    @FXML
    private void handleCalendarView(ActionEvent event) {
        try {
            Main.showCalendarView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTaskCreation(ActionEvent event) {
        try {
            Main.showTaskCreation();
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
    private void handleHomeDashboard(ActionEvent event) {
        try {
            Main.showHomeDashboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

