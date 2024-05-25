package taskmanage.ui;

import taskmanage.controller.impl.TaskController;
import taskmanage.model.impl.Task;
import java.util.List;

public class CalendarView {
    private TaskController taskController;

    public CalendarView() {
        this.taskController = new TaskController();
    }

    public void display() {
        List<Task> tasks = taskController.fetchAllTasks();
        System.out.println("Calendar View:");
        for (Task task : tasks) {
            System.out.println("Task: " + task.getName());
            System.out.println("Tags: " + String.join(", ", task.getTags()));
        }
    }
}
