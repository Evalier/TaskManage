package taskmanage.controller;

import taskmanage.constants.EnumsAndConstants;
import taskmanage.model.Task;
import taskmanage.utility.DataValidator;
import taskmanage.utility.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private DatabaseConnector databaseConnector;

    // Constructor
    public TaskController() {
        this.databaseConnector = new DatabaseConnector();
        System.out.println("TaskController initialized");
    }

    // Method to add a task to the system
    public void addTask(Task task) {
        if (validateTask(task)) {
            String query = "INSERT INTO tasks (name, description, dueDate, completionDate, priority, status, tags) VALUES ('" +
                    task.getName() + "', '" +
                    task.getDescription() + "', '" +
                    task.getDueDate() + "', '" +
                    task.getCompletionDate() + "', '" +
                    task.getPriority() + "', '" +
                    task.getStatus() + "', '" +
                    String.join(",", task.getTags()) + "')";
            databaseConnector.executeUpdate(query);
            System.out.println("Task added: " + task.getName());
        } else {
            System.out.println("Invalid task data. Task not added.");
        }
    }

    // Method to validate a task
    private boolean validateTask(Task task) {
        return DataValidator.validateString(task.getName()) &&
                DataValidator.validateString(task.getDescription()) &&
                DataValidator.validateDate(task.getDueDate()) &&
                (task.getCompletionDate() == null || DataValidator.validateDate(task.getCompletionDate())) &&
                DataValidator.validatePriority(task.getPriority().toString()) &&
                DataValidator.validateStatus(task.getStatus().toString()) &&
                task.getTags().stream().allMatch(DataValidator::validateTag);
    }

    // Method to fetch all tasks from the database
    public List<Task> fetchAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        ResultSet resultSet = databaseConnector.executeQuery(query);
        try {
            while (resultSet != null && resultSet.next()) {
                Task task = new Task(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("dueDate"),
                        EnumsAndConstants.PriorityLevel.valueOf(resultSet.getString("priority"))
                );
                task.setCompletionDate(resultSet.getString("completionDate"));
                task.setStatus(EnumsAndConstants.TaskStatus.valueOf(resultSet.getString("status")));
                String[] tags = resultSet.getString("tags").split(",");
                for (String tag : tags) {
                    task.addTag(tag);
                }
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Method to display or update a view
    public void displayView() {
        System.out.println("TaskController view displayed");
    }

    // Method to close the database connection when done
    public void close() {
        databaseConnector.closeConnection();
    }
}
