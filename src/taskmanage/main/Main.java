package taskmanage.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.controller.TaskViewController;
import taskmanage.model.Task;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load Task View
        FXMLLoader taskViewLoader = new FXMLLoader(getClass().getResource("/TaskView.fxml"));
        Parent taskViewRoot = taskViewLoader.load();
        TaskViewController taskViewController = taskViewLoader.getController();
        Task sampleTask = new Task("Sample Task", "This is a sample task description", "2024-12-31", PriorityLevel.MEDIUM);
        sampleTask.addTag("sample");
        taskViewController.setTask(sampleTask);

        // Load Task Creation View
        FXMLLoader taskCreationLoader = new FXMLLoader(getClass().getResource("/TaskCreation.fxml"));
        Parent taskCreationRoot = taskCreationLoader.load();

        // Load Calendar View
        FXMLLoader calendarViewLoader = new FXMLLoader(getClass().getResource("/CalendarView.fxml"));
        Parent calendarViewRoot = calendarViewLoader.load();

        // Load Home Dashboard View
        FXMLLoader homeDashboardLoader = new FXMLLoader(getClass().getResource("/HomeDashboard.fxml"));
        Parent homeDashboardRoot = homeDashboardLoader.load();

        // Show one of the views (e.g., Home Dashboard)
        primaryStage.setTitle("Home Dashboard");
        primaryStage.setScene(new Scene(homeDashboardRoot));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
