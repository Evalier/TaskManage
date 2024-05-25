package taskmanage.feature.impl;

import taskmanage.feature.interfaces.FeatureInterface;
import taskmanage.model.impl.Reminder;
import taskmanage.model.impl.Task;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReminderManager implements FeatureInterface {
    private List<Reminder> reminders;

    // Constructor
    public ReminderManager() {
        this.reminders = new ArrayList<>();
    }

    @Override
    public void initialize() {
        // Initialization logic for ReminderManager
        System.out.println("Initializing ReminderManager...");
        // Any necessary setup code can go here
    }

    @Override
    public void executeFeature() {
        // Execution logic for ReminderManager feature
        System.out.println("Executing ReminderManager feature...");
        checkReminders();
    }

    // Method to set reminders for tasks
    public void setReminder(Task task, LocalDateTime reminderTime) {
        Reminder reminder = new Reminder(task, reminderTime);
        reminders.add(reminder);
        System.out.println("Reminder set for task: " + task.getName() + " at " + reminderTime);
    }

    // Method to update an existing reminder
    public void updateReminder(Task task, LocalDateTime newReminderTime) {
        for (Reminder reminder : reminders) {
            if (reminder.getTask().equals(task)) {
                reminder.setReminderTime(newReminderTime);
                System.out.println("Reminder updated for task: " + task.getName() + " to " + newReminderTime);
                return;
            }
        }
        System.out.println("No existing reminder found for task: " + task.getName());
    }

    // Method to remove a reminder
    public void removeReminder(Task task) {
        reminders.removeIf(reminder -> reminder.getTask().equals(task));
        System.out.println("Reminder removed for task: " + task.getName());
    }

    // Method to check for upcoming reminders and notify user
    public void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        for (Reminder reminder : reminders) {
            if (reminder.getReminderTime().isBefore(now) || reminder.getReminderTime().isEqual(now)) {
                System.out.println("Reminder: Time to work on task - " + reminder.getTask().getName());
                // Optionally, remove reminder after notifying
                // removeReminder(reminder.getTask());
            }
        }
    }

    @Override
    public String toString() {
        return "ReminderManager{" +
                "reminders=" + reminders +
                '}';
    }
}

