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
import java.time.YearMonth;
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
    private boolean isSpecificMonthView;

    public CalendarViewController() {
        if (dbConnector == null) {
            dbConnector = new UtilityFacade();
        }
        currentDate = LocalDate.now();
        isSpecificMonthView = true; //Initialize the UI on the specific month view
    }

    @Override
    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));

        lblCurrentViewMode.setText("Specific Month View");
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

        //Toggle for all months view and specific month view
        if (isSpecificMonthView)
        {
            populateMonthView();
        }
        else
        {
            populateYearView();
        }
    }

    private void updateMonthYearLabel() {
        String monthYear = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentDate.getYear();
        lblMonthYear.setText(monthYear);
    }

    //Method responsible for the Specific Month View display
    private void populateMonthView()
    {
        System.out.println("Populating specific month view"); //Debugging print statement :P
        monthView.getChildren().clear(); // Clear previous panes

        //month fields
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int dayOfWeekValue = firstOfMonth.getDayOfWeek().getValue(); // Monday is 1 and Sunday is 7
        int daysInMonth = yearMonth.lengthOfMonth(); //find days in the month with method call

        //initialize day counter at 1
        int dayCounter = 1;

        //outer loop for weeks
        for (int i = 0; i < 6; i++) // Assuming a 6-week month view for simplicity
        {
            //inner loop for days
            for (int j = 0; j < 7; j++)
            {
                //first day of week condition
                if (i == 0 && j < dayOfWeekValue % 7)
                {
                    // Empty pane before the first day of the month
                    Pane emptyPane = new Pane();
                    emptyPane.setPrefSize(100, 100);
                    monthView.add(emptyPane, j, i + 1);
                }
                //if still days in month, loop
                else if (dayCounter <= daysInMonth)
                {
                    Pane dayPane = new Pane();
                    dayPane.setPrefSize(100, 100);
                    dayPane.setStyle("-fx-border-color: black;");
                    Label dayLabel = new Label(String.valueOf(dayCounter));
                    dayPane.getChildren().add(dayLabel);
                    monthView.add(dayPane, j, i + 1);
                    dayCounter++;
                }
                //last day of month condition
                else
                {
                    // Empty pane after the last day of the month
                    Pane emptyPane = new Pane();
                    emptyPane.setPrefSize(100, 100);
                    monthView.add(emptyPane, j, i + 1);
                }
            }
        }
    }

    //Method responsible for the Show All Months display
    private void populateYearView()
    {
        System.out.println("Populating all months view"); //Print statement for when I was debugging lol
        monthView.getChildren().clear(); // Clear previous panes

        //outer loop for updating month of the year
        for (int month = 1; month <= 12; month++)
        {
            YearMonth yearMonth = YearMonth.of(currentDate.getYear(), month);
            LocalDate firstOfMonth = yearMonth.atDay(1);
            int dayOfWeekValue = firstOfMonth.getDayOfWeek().getValue(); // Monday is 1 and Sunday is 7


            int daysInMonth = yearMonth.lengthOfMonth();
            int row = (month - 1) / 3; // 4 rows for 12 months
            int col = (month - 1) % 3; // 3 columns

            //show the grid
            GridPane monthGrid = new GridPane();
            monthGrid.setGridLinesVisible(true);

            //set the month label
            Label monthLabel = new Label(firstOfMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
            monthGrid.add(monthLabel, 0, 0, 7, 1);

            //initialize day counter at 1
            int dayCounter = 1;

            //inner loop for updating month days
            for (int i = 1; i <= 6; i++)
            { // Assuming a 6-week month view for simplicity
                for (int j = 0; j < 7; j++)
                {
                    //condition for first square of the month
                    if (i == 1 && j < dayOfWeekValue % 7)
                    {
                        // Empty pane before the first day of the month
                        Pane emptyPane = new Pane();
                        emptyPane.setPrefSize(30, 30);
                        monthGrid.add(emptyPane, j, i);
                    }
                    //if still days in the month, loop
                    else if (dayCounter <= daysInMonth)
                    {
                        //pane appearance; day and position
                        Pane dayPane = new Pane();
                        dayPane.setPrefSize(30, 30);
                        dayPane.setStyle("-fx-border-color: black;");
                        Label dayLabel = new Label(String.valueOf(dayCounter));
                        dayPane.getChildren().add(dayLabel);
                        monthGrid.add(dayPane, j, i);
                        dayCounter++;
                    }
                    //condition for end of the month
                    else
                    {
                        // Empty pane after the last day of the month
                        Pane emptyPane = new Pane();
                        emptyPane.setPrefSize(30, 30);
                        monthGrid.add(emptyPane, j, i);
                    }
                }
            }
            //add the month
            monthView.add(monthGrid, col, row);
        }
    }

    @FXML
    private void handlePreviousMonth() {
        currentDate = currentDate.minusMonths(1);
        updateMonthYearLabel(); //to update year label
        loadTasks();
        populateMonthView(); //to populate month grid
    }

    @FXML
    private void handleNextMonth() {
        currentDate = currentDate.plusMonths(1);
        updateMonthYearLabel(); //to update month label
        loadTasks();
        populateMonthView(); //to populate the month grid
    }

    @FXML
    private void handleSpecificMonthView() {
        System.out.println("Switching to specific month view"); // Debug statement
        lblCurrentViewMode.setText("Specific Month View");
        isSpecificMonthView = true;
        btnPreviousMonth.setVisible(true);
        btnNextMonth.setVisible(true);
        updateMonthYearLabel();
        populateMonthView();
    }

    @FXML
    private void handleShowAllMonths() {
        System.out.println("Switching to all months view"); // Debug statement
        lblCurrentViewMode.setText("All Months View");
        isSpecificMonthView = false;
        btnPreviousMonth.setVisible(false);
        btnNextMonth.setVisible(false);
        populateYearView();
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
