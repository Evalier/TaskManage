import java.util.List;

/**
 * The HomeDashboard class is the main interface screen for the task management application.
 * It displays summaries of tasks and provides navigation to other functionalities like task creation, task view, and calendar.
 */
public class HomeDashboard {
    private List<Task> completedTasks;
    private List<Task> ongoingTasks;
    private List<Task> overdueTasks;

    /**
     * Constructor initializes the dashboard with lists of tasks.
     *
     * @param completedTasks List of completed tasks
     * @param ongoingTasks   List of ongoing tasks
     * @param overdueTasks   List of overdue tasks
     */
    public HomeDashboard(List<Task> completedTasks, List<Task> ongoingTasks, List<Task> overdueTasks) {
        this.completedTasks = completedTasks;
        this.ongoingTasks = ongoingTasks;
        this.overdueTasks = overdueTasks;
    }

    /**
     * Displays summary of all types of tasks.
     */
    public void displayTaskSummary() {
        System.out.println("Completed Tasks: " + completedTasks.size());
        System.out.println("Ongoing Tasks: " + ongoingTasks.size());
        System.out.println("Overdue Tasks: " + overdueTasks.size());
    }

    /**
     * Placeholder method for displaying statistical graphics.
     */
    public void displayStatisticalGraphics() {
        System.out.println("Displaying statistical graphics related to task performance...");
    }

    // Getters and setters
    public List<Task> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(List<Task> completedTasks) {
        this.completedTasks = completedTasks;
    }

    public List<Task> getOngoingTasks() {
        return ongoingTasks;
    }

    public void setOngoingTasks(List<Task> ongoingTasks) {
        this.ongoingTasks = ongoingTasks;
    }

    public List<Task> getOverdueTasks() {
        return overdueTasks;
    }

    public void setOverdueTasks(List<Task> overdueTasks) {
        this.overdueTasks = overdueTasks;
    }
}

/**
 * Task class to represent individual tasks with minimal attributes.
 */
class Task {
    private String name;
    private boolean isComplete;

    // Constructor, getters and setters
    public Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
