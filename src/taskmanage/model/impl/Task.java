package taskmanage.model.impl;

import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.constants.EnumsAndConstants.TaskStatus;
import taskmanage.utility.facades.UtilityFacade;
import taskmanage.model.interfaces.ModelInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task implements ModelInterface {
    private int id;
    private String name;
    private String description;
    private String dueDate;
    private String completionDate;
    private PriorityLevel priority;
    private TaskStatus status;
    private List<SubTask> subTasks = new ArrayList<>();
    private Set<String> tags = new HashSet<>();
    private String category;
    private String reminders;

    // No-parameter constructor
    public Task() {
        this.subTasks = new ArrayList<>();
        this.tags = new HashSet<>();
    }

    // Constructor
    public Task(int id, String name, String description, String dueDate, String completionDate, PriorityLevel priority, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.completionDate = completionDate;
        this.priority = priority;
        this.status = status;
        this.subTasks = new ArrayList<>();
        this.tags = new HashSet<>();
    }

    // Another constructor matching the usage in CalendarViewController
    public Task(String name, String description, String dueDate, PriorityLevel priority) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.subTasks = new ArrayList<>();
        this.tags = new HashSet<>();
    }

    // Getters and Setters
    public int getID() {
        return id;
    }

    public void setID(int id) {
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

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void updateStatus(Task task) {
        boolean allSubTasksComplete = true;
        for (SubTask subTask : task.getSubTasks()) {
            if (subTask.getStatus() != TaskStatus.COMPLETE) {
                allSubTasksComplete = false;
                break;
            }
        }
        if (allSubTasksComplete) {
            task.setStatus(TaskStatus.COMPLETE);
        } else {
            task.setStatus(TaskStatus.PENDING);
        }
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

    public void addSubtask(String subtask) {
        this.subTasks.add(new SubTask(subtask));
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReminders() {
        return reminders;
    }

    public void setReminders(String reminders) {
        this.reminders = reminders;
    }

    @Override
    public boolean validate() {
        return true; // Add actual validation logic
    }

    @Override
    public void save() {
        if (!validate()) {
            System.out.println("Validation failed. Cannot save.");
            return;
        }

        UtilityFacade dbConnector = new UtilityFacade();
        String query = "INSERT INTO tasks (name, description, dueDate, completionDate, priority, status, category, reminders) VALUES (values)"; // Replace with actual table name and fields
        dbConnector.executeUpdate(query);
        System.out.println("Record saved.");
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", completionDate='" + completionDate + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", subTasks=" + subTasks +
                ", tags=" + tags +
                ", category='" + category + '\'' +
                ", reminders='" + reminders + '\'' +
                '}';
    }
}


