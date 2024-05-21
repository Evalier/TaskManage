package taskmanage.model;

import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.constants.EnumsAndConstants.TaskStatus;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task {
    private String name;
    private String description;
    private String dueDate;
    private String completionDate;
    private PriorityLevel priority;
    private TaskStatus status;
    private List<SubTask> subTasks;
    private Set<String> tags; // New field for tags

    // Constructor
    public Task(String name, String description, String dueDate, PriorityLevel priority) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.PENDING;  // Default status
        this.subTasks = new ArrayList<>();
        this.tags = new HashSet<>();
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

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
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

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    // Methods to handle subtasks
    public void addSubTask(SubTask subTask) {
        this.subTasks.add(subTask);
    }

    public void removeSubTask(SubTask subTask) {
        this.subTasks.remove(subTask);
    }

    // Method to update task status based on subtask completion
    public void updateStatus() {
        boolean allSubTasksComplete = true;
        for (SubTask subTask : subTasks) {
            if (subTask.getStatus() != TaskStatus.COMPLETE) {
                allSubTasksComplete = false;
                break;
            }
        }
        if (allSubTasksComplete) {
            this.status = TaskStatus.COMPLETE;
        } else {
            this.status = TaskStatus.PENDING;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", completionDate='" + completionDate + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", subTasks=" + subTasks +
                ", tags=" + tags +
                '}';
    }
}
