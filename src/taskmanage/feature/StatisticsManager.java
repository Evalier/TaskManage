package taskmanage.feature;

import taskmanage.constants.EnumsAndConstants;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.constants.EnumsAndConstants.TaskStatus;
import taskmanage.model.Task;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class StatisticsManager {
    private List<Task> tasks;

    // Constructor
    public StatisticsManager() {
        this.tasks = new ArrayList<>();
    }

    // Method to add a task to the statistics manager
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Method to calculate the task completion rate
    public double calculateCompletionRate() {
        int completedTasks = 0;
        for (Task task : tasks) {
            if (task.getStatus() == EnumsAndConstants.TaskStatus.COMPLETE) {
                completedTasks++;
            }
        }
        return (double) completedTasks / tasks.size();
    }

    // Method to calculate the average time taken to complete tasks
    public double calculateAverageCompletionTime() {
        int totalCompletionTime = 0;
        int completedTasks = 0;
        for (Task task : tasks) {
            if (task.getStatus() == EnumsAndConstants.TaskStatus.COMPLETE) {
                // Assuming dueDate and completionDate are in a format that can be parsed into LocalDate
                LocalDate dueDate = LocalDate.parse(task.getDueDate());
                LocalDate completionDate = LocalDate.parse(task.getCompletionDate()); // You need to add completionDate to Task class
                totalCompletionTime += ChronoUnit.DAYS.between(dueDate, completionDate);
                completedTasks++;
            }
        }
        return completedTasks > 0 ? (double) totalCompletionTime / completedTasks : 0;
    }

    // Method to calculate the frequency of overdue tasks
    public double calculateOverdueFrequency() {
        int overdueTasks = 0;
        for (Task task : tasks) {
            if (task.getStatus() == EnumsAndConstants.TaskStatus.OVERDUE) {
                overdueTasks++;
            }
        }
        return (double) overdueTasks / tasks.size();
    }

    // Method to generate a report with calculated statistics
    public String generateStatisticsReport() {
        double completionRate = calculateCompletionRate();
        double averageCompletionTime = calculateAverageCompletionTime();
        double overdueFrequency = calculateOverdueFrequency();

        return "Task Statistics Report:\n" +
                "Completion Rate: " + (completionRate * 100) + "%\n" +
                "Average Completion Time: " + averageCompletionTime + " days\n" +
                "Overdue Frequency: " + (overdueFrequency * 100) + "%\n";
    }

    // Method to reset statistics
    public void resetStatistics() {
        tasks.clear();
    }

    // Additional methods for more statistics can be added here

    @Override
    public String toString() {
        return "StatisticsManager{" +
                "tasks=" + tasks +
                '}';
    }
}
