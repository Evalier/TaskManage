package taskmanage.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.controller.impl.TaskViewController;
import taskmanage.model.impl.Task;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        showHomeDashboard();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showHomeDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/HomeDashboard.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Home Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showTaskView() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/TaskView.fxml"));
        Parent root = loader.load();
        TaskViewController controller = loader.getController();
        Task sampleTask = new Task("Sample Task", "This is a sample task description", "2024-12-31", PriorityLevel.MEDIUM);
        sampleTask.addTag("sample");
        controller.setTask(sampleTask);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Task View");
        primaryStage.show();
    }

    public static void showTaskCreation() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/TaskCreation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Task Creation");
        primaryStage.show();
    }

    public static void showCalendarView() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/CalendarView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calendar View");
        primaryStage.show();
    }
}


