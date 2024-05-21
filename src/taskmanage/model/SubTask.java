package taskmanage.model;

import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.constants.EnumsAndConstants.TaskStatus;

public class SubTask {
    private String name;
    private String description;
    private String dueDate;
    private PriorityLevel priority;
    private TaskStatus status;

    // Constructor
    public SubTask(String name, String description, String dueDate, PriorityLevel priority) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.PENDING;  // Default status
    }

    // Getters and Setters
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
    public String toString() {
        return "SubTask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}

