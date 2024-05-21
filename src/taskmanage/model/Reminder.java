package taskmanage.model;

import java.time.LocalDateTime;

public class Reminder {
    private Task task;
    private LocalDateTime reminderTime;

    // Constructor
    public Reminder(Task task, LocalDateTime reminderTime) {
        this.task = task;
        this.reminderTime = reminderTime;
    }

    // Getters and Setters
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "task=" + task +
                ", reminderTime=" + reminderTime +
                '}';
    }
}

