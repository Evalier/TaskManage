package taskmanage.controller;
import taskmanage.model.Task;
public class ViewController {
    // Constructor
    public ViewController() {
        // Initialization code
        System.out.println("ViewController initialized");
    }

    // Example methods that you might have in a controller
    public void addTask(Task task) {
        // Add a task to the system
        System.out.println("Task added: " + task.getName());
    }

    public void displayView() {
        // Display or update a view
        System.out.println("ViewController view displayed");
    }
}
