package taskmanage.model.impl;

import taskmanage.utility.impl.DatabaseConnector;
import taskmanage.model.interfaces.ModelInterface;
import taskmanage.utility.facades.UtilityFacade;

import java.time.LocalDateTime;

public class Reminder implements ModelInterface {
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
    public boolean validate() {
        // Basic validation logic
        return true; // Replace with actual validation logic for your class
    }

    @Override
    public void save() {
        if (!validate()) {
            System.out.println("Validation failed. Cannot save.");
            return;
        }

        UtilityFacade dbConnector = new UtilityFacade();
        String query = "INSERT INTO tablename (fields) VALUES (values)"; // Replace with actual table name and fields
        dbConnector.executeUpdate(query);
        System.out.println("Record saved.");
    }


    @Override
    public String toString() {
        return "Reminder{" +
                "task=" + task +
                ", reminderTime=" + reminderTime +
                '}';
    }
}

