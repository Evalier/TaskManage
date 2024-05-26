package taskmanage.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import taskmanage.constants.EnumsAndConstants;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.controller.interfaces.ControllerInterface;
import taskmanage.model.impl.Task;
import taskmanage.utility.impl.DatabaseConnector;
import taskmanage.utility.facades.UtilityFacade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class HomeDashboardController implements ControllerInterface {
    @FXML private Label completedTasksLabel;
    @FXML private Label ongoingTasksLabel;
    @FXML private Label overdueTasksLabel;
    @FXML private BarChart<String, Number> taskBarChart;
    @FXML private TextArea analysisTextArea;

    @FXML private Label summaryLabel;
    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, String> nameColumn;
    @FXML private TableColumn<Task, String> descriptionColumn;
    @FXML private TableColumn<Task, String> dueDateColumn;
    @FXML private TableColumn<Task, String> priorityColumn;
    @FXML private TableColumn<Task, String> statusColumn;
    @FXML private TableColumn<Task, String> tagsColumn;
    @FXML private Button refreshButton;

    @FXML private Button calendarButton;
    @FXML private Button taskCreationButton;
    @FXML private Button taskViewButton;

    private static UtilityFacade dbConnector;

    public HomeDashboardController() {
        if (dbConnector == null) {
            dbConnector = new UtilityFacade();
        }
    }

    @Override
    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));

        loadTasks();
        updateTaskSummary();
        updateStatisticalGraphics();
    }

    @FXML
    public void handleRefresh() {
        loadTasks();
        updateTaskSummary();
        updateStatisticalGraphics();
    }

    @FXML
    public void handleCalendar(ActionEvent event) {
        // Handle calendar button action
        System.out.println("Calendar button clicked");
        // Implement the logic to switch to the Calendar screen
    }

    @FXML
    public void handleTaskCreation(ActionEvent event) {
        // Handle task creation button action
        System.out.println("Task Creation button clicked");
        // Implement the logic to switch to the Task Creation screen
    }

    @FXML
    public void handleTaskView(ActionEvent event) {
        // Handle task view button action
        System.out.println("Task View button clicked");
        // Implement the logic to switch to the Task View screen
    }

    @Override
    public void handleActionEvent(ActionEvent event) {
        // Default implementation, if needed
    }

    private void loadTasks() {
        List<Task> tasks = fetchAllTasks();
        taskTable.getItems().setAll(tasks);

        // Update summary label
        long completedTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("COMPLETE")).count();
        long pendingTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("PENDING")).count();
        summaryLabel.setText(String.format("Task Summary: %d completed, %d pending", completedTasks, pendingTasks));
    }

    private void updateTaskSummary() {
        List<Task> tasks = fetchAllTasks();
        long completedTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("COMPLETE")).count();
        long ongoingTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("IN_PROGRESS")).count();
        long overdueTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("OVERDUE")).count();

        completedTasksLabel.setText("Completed Tasks: " + completedTasks);
        ongoingTasksLabel.setText("Ongoing Tasks: " + ongoingTasks);
        overdueTasksLabel.setText("Overdue Tasks: " + overdueTasks);
    }

    private void updateStatisticalGraphics() {
        taskBarChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Task Statistics");

        List<Task> tasks = fetchAllTasks();
        long completedTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("COMPLETE")).count();
        long pendingTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("PENDING")).count();
        long overdueTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("OVERDUE")).count();

        series.getData().add(new XYChart.Data<>("Completed", completedTasks));
        series.getData().add(new XYChart.Data<>("Pending", pendingTasks));
        series.getData().add(new XYChart.Data<>("Overdue", overdueTasks));

        taskBarChart.getData().add(series);

        // Example analysis text
        analysisTextArea.setText("Analysis:\n- Try to reduce the number of overdue tasks.\n- Focus on completing pending tasks.");
    }

    public List<Task> fetchAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Connection connection = dbConnector.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("dueDate"),
                        PriorityLevel.valueOf(resultSet.getString("priority"))
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
