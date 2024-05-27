package taskmanage.controller.facades;

import javafx.event.ActionEvent;
import taskmanage.constants.EnumsAndConstants;
import taskmanage.controller.impl.*;
import taskmanage.model.impl.Task;
import taskmanage.controller.impl.TaskCreationController;

import java.util.List;

public class ControllerFacade {
    private final CalendarViewController calendarViewController;
    private final HomeDashboardController homeDashboardController;
    private final TaskController taskController;
    private final TaskCreationController taskCreationController;
    private final TaskViewController taskViewController;

    public ControllerFacade() {
        this.calendarViewController = new CalendarViewController();
        this.homeDashboardController = new HomeDashboardController();
        this.taskController = new TaskController();
        this.taskCreationController = new TaskCreationController();
        this.taskViewController = new TaskViewController();
    }

    // Methods for CalendarViewController
    public void initializeCalendarViewController() {
        calendarViewController.initialize();
    }

    public void handleCalendarViewActionEvent(ActionEvent event) {
        calendarViewController.handleActionEvent(event);
    }

    public void refreshCalendarView() {
        calendarViewController.handleRefresh();
    }

    public List<Task> getAllCalendarViewTasks() {
        return calendarViewController.fetchAllTasks();
    }

    // Methods for HomeDashboardController
    public void initializeHomeDashboardController() {
        homeDashboardController.initialize();
    }

    public void handleHomeDashboardActionEvent(ActionEvent event) {
        homeDashboardController.handleActionEvent(event);
    }

    public void refreshHomeDashboard() {
        homeDashboardController.handleRefresh();
    }

    public List<Task> getAllHomeDashboardTasks() {
        return homeDashboardController.fetchAllTasks();
    }

    // Methods for TaskController
    public void initializeTaskController() {
        taskController.initialize();
    }

    public void handleTaskControllerActionEvent(ActionEvent event) {
        taskController.handleActionEvent(event);
    }

    public void addTask(Task task) {
        taskController.addTask(task);
    }

    public void deleteTask(Task task) {
        taskController.deleteTask(task);
    }

    public List<Task> fetchAllTasks() {
        return taskController.fetchAllTasks();
    }

    public void displayTaskView() {
        taskController.displayView();
    }

    public void closeTaskController() {
        taskController.close();
    }

    // Methods for TaskCreationController
    public void saveTask(String name, String description, String dueDate, EnumsAndConstants.PriorityLevel priority, List<String> tags) {
        taskCreationController.handleSaveTask(name, description, dueDate, priority, tags);
    }



    // Methods for TaskViewController
    public void refreshTaskView() {
        taskViewController.handleRefresh();
    }

    public List<Task> getAllTasks() {
        return taskViewController.fetchTasks();
    }

    public void deleteTask(int taskId) {
        taskViewController.deleteTaskFromDatabase(taskId);
    }
}

