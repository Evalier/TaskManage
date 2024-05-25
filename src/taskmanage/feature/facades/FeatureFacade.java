package taskmanage.feature.facades;

import taskmanage.feature.impl.HabitTracker;
import taskmanage.feature.impl.PriorityManager;
import taskmanage.feature.impl.ReminderManager;
import taskmanage.feature.impl.StatisticsManager;
import taskmanage.model.impl.Habit;
import taskmanage.model.impl.Reminder;
import taskmanage.model.impl.Task;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import java.time.LocalDateTime;
import java.util.List;

public class FeatureFacade {
    private final HabitTracker habitTracker;
    private final PriorityManager priorityManager;
    private final ReminderManager reminderManager;
    private final StatisticsManager statisticsManager;

    public FeatureFacade() {
        this.habitTracker = new HabitTracker();
        this.priorityManager = new PriorityManager();
        this.reminderManager = new ReminderManager();
        this.statisticsManager = new StatisticsManager();
    }

    // Methods for HabitTracker
    public void initializeHabitTracker() {
        habitTracker.initialize();
    }

    public void executeHabitTrackerFeature() {
        habitTracker.executeFeature();
    }

    public void addHabit(Habit habit) {
        habitTracker.addHabit(habit);
    }

    public void removeHabit(Habit habit) {
        habitTracker.removeHabit(habit);
    }

    public List<Habit> getHabits() {
        return habitTracker.getHabits();
    }

    public void analyzeHabits() {
        habitTracker.analyzeHabits();
    }

    public void displayHabitInsights() {
        habitTracker.displayHabitInsights();
    }

    // Methods for PriorityManager
    public void initializePriorityManager() {
        priorityManager.initialize();
    }

    public void executePriorityManagerFeature() {
        priorityManager.executeFeature();
    }

    public void setPriority(Task task, PriorityLevel priority) {
        priorityManager.setPriority(task, priority);
    }

    public void addTask(Task task) {
        priorityManager.addTask(task);
    }

    public List<Task> getTasksByPriority(PriorityLevel priority) {
        return priorityManager.getTasksByPriority(priority);
    }

    public void displayTasksByPriority() {
        priorityManager.displayTasksByPriority();
    }

    // Methods for ReminderManager
    public void initializeReminderManager() {
        reminderManager.initialize();
    }

    public void executeReminderManagerFeature() {
        reminderManager.executeFeature();
    }

    public void setReminder(Task task, LocalDateTime reminderTime) {
        reminderManager.setReminder(task, reminderTime);
    }

    public void updateReminder(Task task, LocalDateTime newReminderTime) {
        reminderManager.updateReminder(task, newReminderTime);
    }

    public void removeReminder(Task task) {
        reminderManager.removeReminder(task);
    }

    public void checkReminders() {
        reminderManager.checkReminders();
    }

    // Methods for StatisticsManager
    public void initializeStatisticsManager() {
        statisticsManager.initialize();
    }

    public void executeStatisticsManagerFeature() {
        statisticsManager.executeFeature();
    }

    public void addTaskToStatistics(Task task) {
        statisticsManager.addTask(task);
    }

    public double calculateCompletionRate() {
        return statisticsManager.calculateCompletionRate();
    }

    public double calculateAverageCompletionTime() {
        return statisticsManager.calculateAverageCompletionTime();
    }

    public double calculateOverdueFrequency() {
        return statisticsManager.calculateOverdueFrequency();
    }

    public String generateStatisticsReport() {
        return statisticsManager.generateStatisticsReport();
    }

    public void resetStatistics() {
        statisticsManager.resetStatistics();
    }
}
