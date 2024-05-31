package taskmanage.controller.impl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import taskmanage.controller.interfaces.ControllerInterface;
import taskmanage.main.Main;
import taskmanage.model.impl.Task;
import taskmanage.utility.facades.UtilityFacade;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
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
    private final TaskController taskController;

    public CalendarViewController() {
        if (dbConnector == null) {
            dbConnector = new UtilityFacade();
        }
        currentDate = LocalDate.now();
        isSpecificMonthView = true; // Initialize the UI on the specific month view
        taskController = new TaskController();
    }

    @Override
    @FXML
    public void initialize()
    {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));

        //Initializes as Specific Month
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
    public void handleRefresh()
    {
        loadTasks();

        // Toggle for all months view and specific month view
        if (isSpecificMonthView)
        {
            populateMonthView();
        }
        else
        {
            populateYearView();
        }
    }

    //Method to update the month and year label at the top of the calendar
    private void updateMonthYearLabel()
    {
        String monthYear = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentDate.getYear();
        lblMonthYear.setText(monthYear);
    }

    // Method responsible for the Specific Month View display
    private void populateMonthView()
    {
        System.out.println("Populating specific month view"); // Debugging print statement :P
        monthView.getChildren().clear(); // Clear previous panes

        // Month fields
        YearMonth yearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int dayOfWeekValue = firstOfMonth.getDayOfWeek().getValue(); // Monday is 1 and Sunday is 7
        int daysInMonth = yearMonth.lengthOfMonth(); // Find days in the month with method call

        // Initialize day counter at 1
        int dayCounter = 1;

        // Outer loop for weeks
        for (int i = 0; i < 6; i++)
        { // Assuming a 6-week month view for simplicity
            // Inner loop for days
            for (int j = 0; j < 7; j++)
            {
                // First day of week condition
                if (i == 0 && j < dayOfWeekValue % 7)
                {
                    // Empty pane before the first day of the month
                    Pane emptyPane = new Pane();
                    emptyPane.setPrefSize(100, 100);
                    monthView.add(emptyPane, j, i + 1);
                }
                // If still days in month, loop
                else if (dayCounter <= daysInMonth)
                {
                    Button dayButton = new Button(String.valueOf(dayCounter));
                    dayButton.setPrefSize(100, 100);
                    dayButton.setOnAction(e -> handleDayButtonClick(dayButton.getText()));
                    monthView.add(dayButton, j, i + 1);
                    dayCounter++;
                }
                // Last day of month condition
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

    private void handleDayButtonClick(String day) {
        System.out.println("Day " + day + " clicked");
        // You can add additional logic to handle the day button click event here
    }

    // Method responsible for the Show All Months display
    private void populateYearView() {
        System.out.println("Populating all months view"); // Print statement for when I was debugging lol
        monthView.getChildren().clear(); // Clear previous panes

        // Outer loop for updating month of the year
        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(currentDate.getYear(), month);
            LocalDate firstOfMonth = yearMonth.atDay(1);
            int dayOfWeekValue = firstOfMonth.getDayOfWeek().getValue(); // Monday is 1 and Sunday is 7

            int daysInMonth = yearMonth.lengthOfMonth();
            int row = (month - 1) / 3; // 4 rows for 12 months
            int col = (month - 1) % 3; // 3 columns

            // Show the grid
            GridPane monthGrid = new GridPane();
            monthGrid.setGridLinesVisible(true);

            // Set the month label
            Label monthLabel = new Label(firstOfMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
            monthGrid.add(monthLabel, 0, 0, 7, 1);

            // Initialize day counter at 1
            int dayCounter = 1;

            // Inner loop for updating month days
            for (int i = 1; i <= 6; i++) { // Assuming a 6-week month view for simplicity
                for (int j = 0; j < 7; j++) {
                    // Condition for first square of the month
                    if (i == 1 && j < dayOfWeekValue % 7) {
                        // Empty pane before the first day of the month
                        Pane emptyPane = new Pane();
                        emptyPane.setPrefSize(30, 30);
                        monthGrid.add(emptyPane, j, i);
                    }
                    // If still days in the month, loop
                    else if (dayCounter <= daysInMonth) {
                        Button dayButton = new Button(String.valueOf(dayCounter));
                        dayButton.setPrefSize(30, 30);
                        dayButton.setOnAction(e -> handleDayButtonClick(dayButton.getText()));
                        monthGrid.add(dayButton, j, i);
                        dayCounter++;
                    }
                    // Condition for end of the month
                    else {
                        // Empty pane after the last day of the month
                        Pane emptyPane = new Pane();
                        emptyPane.setPrefSize(30, 30);
                        monthGrid.add(emptyPane, j, i);
                    }
                }
            }
            // Add the month
            monthView.add(monthGrid, col, row);
        }
    }

    @FXML
    private void handlePreviousMonth()
    {
        currentDate = currentDate.minusMonths(1);
        updateMonthYearLabel(); // To update year label
        loadTasks();
        populateMonthView(); // To populate month grid
    }

    @FXML
    private void handleNextMonth() {
        currentDate = currentDate.plusMonths(1);
        updateMonthYearLabel(); // To update month label
        loadTasks();
        populateMonthView(); // To populate the month grid
    }

    @FXML
    private void handleSpecificMonthView()
    {
        System.out.println("Switching to specific month view"); // Debug statement
        lblCurrentViewMode.setText("Specific Month View");
        isSpecificMonthView = true;
        btnPreviousMonth.setVisible(true);
        btnNextMonth.setVisible(true);
        updateMonthYearLabel();
        populateMonthView();
    }

    @FXML
    private void handleShowAllMonths()
    {
        System.out.println("Switching to all months view"); //Debug print
        lblCurrentViewMode.setText("All Months View");
        isSpecificMonthView = false;
        btnPreviousMonth.setVisible(false);
        btnNextMonth.setVisible(false);
        populateYearView();
    }

    @FXML
    private void handleEditTask() { //STILL WORKING ON - Alex
        System.out.println("Edit task button clicked");
        // Implement edit task functionality here
    }

    @FXML
    //Method for delete task UI button functionality
    //Deletes a selected task from the calendar
    //may require further database integration
    private void handleDeleteTask()
    {
        //Get the table of tasks
        //May need to be modified to function with database
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();

        //if task selected, delete it
        if (selectedTask != null)
        {
            taskController.deleteTask(selectedTask);
            loadTasks(); // Refresh the task list
        }
        //if no task selected, do nothing
        else
        {
            System.out.println("No task selected for deletion.");
        }
    }

    //Method for the calendar to fetch all tasks
    //The ControllerFacade needed a reference the method in this class
    public List<Task> fetchAllTasks()
    {
        //Call to taskController
        return taskController.fetchAllTasks();
    }

    private void loadTasks() {
        List<Task> tasks = fetchAllTasks();
        taskTable.getItems().setAll(tasks);
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
