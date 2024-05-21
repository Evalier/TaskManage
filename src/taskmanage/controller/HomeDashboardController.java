package taskmanage.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import taskmanage.model.Task;

import java.util.List;

public class HomeDashboardController {
    @FXML private Label summaryLabel;
    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, String> nameColumn;
    @FXML private TableColumn<Task, String> descriptionColumn;
    @FXML private TableColumn<Task, String> dueDateColumn;
    @FXML private TableColumn<Task, String> priorityColumn;
    @FXML private TableColumn<Task, String> statusColumn;
    @FXML private TableColumn<Task, String> tagsColumn;

    private TaskController taskController;

    public HomeDashboardController() {
        taskController = new TaskController();
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));

        loadTasks();
    }

    @FXML
    private void handleRefresh() {
        loadTasks();
    }

    private void loadTasks() {
        List<Task> tasks = taskController.fetchAllTasks();
        taskTable.getItems().setAll(tasks);

        // Update summary label
        long completedTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("COMPLETE")).count();
        long pendingTasks = tasks.stream().filter(task -> task.getStatus().toString().equals("PENDING")).count();
        summaryLabel.setText(String.format("Task Summary: %d completed, %d pending", completedTasks, pendingTasks));
    }
}
