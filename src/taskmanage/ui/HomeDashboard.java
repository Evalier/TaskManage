package taskmanage.ui;

import taskmanage.controller.TaskController;
import taskmanage.model.Task;
import java.util.List;

public class HomeDashboard {
    private TaskController taskController;

    public HomeDashboard() {
        this.taskController = new TaskController();
    }

    public void display() {
        List<Task> tasks = taskController.fetchAllTasks();
        System.out.println("Home Dashboard:");
        for (Task task : tasks) {
            System.out.println("Task: " + task.getName());
            System.out.println("Tags: " + String.join(", ", task.getTags()));
        }
    }
}
