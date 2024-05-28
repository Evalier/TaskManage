package taskmanage.model.impl;

import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.constants.EnumsAndConstants.TaskStatus;
import taskmanage.utility.facades.UtilityFacade;
import taskmanage.model.interfaces.ModelInterface;

public class SubTask implements ModelInterface {

    private int id;
    private String name;
    private String description;
    private String dueDate;
    private PriorityLevel priority;
    private TaskStatus status;

    // Constructor
    public SubTask(int id, String name, String description, String dueDate, PriorityLevel priority, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    // New constructor to accept only a String for name
    public SubTask(String name) {
        this.name = name;
        this.status = TaskStatus.PENDING;  // Default status
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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
        return "SubTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}


