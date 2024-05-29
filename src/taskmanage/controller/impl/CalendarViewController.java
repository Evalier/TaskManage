package taskmanage.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
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
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CalendarViewController implements ControllerInterface {
    @FXML private Button btnPreviousMonth;
    @FXML private Button btnNextMonth;
    @FXML private Button btnShowAllMonths;
    @FXML private Button btnSpecificMonthView;
    @FXML private Label lblMonthYear;
    @FXML private Label lblCurrentViewMode;
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
    @FXML private GridPane monthView;

    private static UtilityFacade dbConnector;
    private LocalDate currentDate;

    public CalendarViewController() {
        if (dbConnector == null) {
            dbConnector = new UtilityFacade();
        }
        currentDate = LocalDate.now();
    }

    @Override
    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));

        lblCurrentViewMode.setText("All Months View");
        updateMonthYearLabel();
        loadTasks();
        populateMonthView();
    }

    @Override
    public void handleActionEvent(ActionEvent event) {
        // Default implementation
    }

    @FXML
    public void handleRefresh() {
        loadTasks();
        populateMonthView();
    }

    //Method to update the month
    private void updateMonthYearLabel() {
        String monthYear = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentDate.getYear();
        lblMonthYear.setText(monthYear);
    }

    //Method to populate the grid calendar view display
    private void populateMonthView()
    {
        monthView.getChildren().clear(); // Clear previous panes
        for (int i = 0; i < 5; i++)
        { // Assuming a 5-week month view
            for (int j = 0; j < 7; j++)
            {
                //Pane panel settings subject to change
                Pane dayPane = new Pane();
                dayPane.setPrefSize(100, 100); // Set preferred size for each day pane
                dayPane.setStyle("-fx-border-color: black;"); // Add border for visibility
                monthView.add(dayPane, j, i + 1); // Add the pane to the grid
            }
        }
    }

    @FXML
    private void handlePreviousMonth() {
        currentDate = currentDate.minusMonths(1);
        updateMonthYearLabel();
        loadTasks();
        populateMonthView();
    }

    @FXML
    private void handleNextMonth() {
        currentDate = currentDate.plusMonths(1);
        updateMonthYearLabel();
        loadTasks();
        populateMonthView();
    }

    @FXML
    private void handleSpecificMonthView() {
        lblCurrentViewMode.setText("Specific Month View");
        System.out.println("Specific month view button clicked");
    }

    @FXML
    private void handleShowAllMonths() {
        lblCurrentViewMode.setText("All Months View");
        System.out.println("Show all months button clicked");
    }

    @FXML
    private void handleEditTask() {
        System.out.println("Edit task button clicked");
    }

    @FXML
    private void handleDeleteTask() {
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
