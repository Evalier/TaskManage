package taskmanage.ui;

import taskmanage.model.Task;

public class TaskView {
    private Task task;

    public TaskView(Task task) {
        this.task = task;
    }

    public void display() {
        System.out.println("Task View:");
        System.out.println("Task: " + task.getName());
        System.out.println("Tags: " + String.join(", ", task.getTags()));
    }
}
