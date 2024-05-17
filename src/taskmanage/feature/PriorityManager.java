package taskmanage.feature;

import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.model.Task;
import java.util.ArrayList;
import java.util.List;

public class PriorityManager {
    private List<Task> tasks;

    // Constructor
    public PriorityManager() {
        this.tasks = new ArrayList<>();
    }

    // Method to set task priority
    public void setPriority(Task task, PriorityLevel priority) {
        task.setPriority(priority);
        System.out.println("Priority set for task: " + task.getName());
    }

    // Method to add a task to the priority manager
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Method to get tasks by priority
    public List<Task> getTasksByPriority(PriorityLevel priority) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    // Method to display tasks by priority on the home dashboard
    public void displayTasksByPriority() {
        System.out.println("Tasks by Priority:");

        for (PriorityLevel priority : PriorityLevel.values()) {
            System.out.println("Priority: " + priority);
            List<Task> filteredTasks = getTasksByPriority(priority);
            for (Task task : filteredTasks) {
                System.out.println(" - " + task.getName() + ": " + task.getDescription());
            }
        }
    }

    // Additional methods for priority management can be added here

    @Override
    public String toString() {
        return "PriorityManager{" +
                "tasks=" + tasks +
                '}';
    }
}

