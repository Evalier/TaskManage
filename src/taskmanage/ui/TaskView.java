package taskmanage.ui;

import taskmanage.controller.TaskController;
import taskmanage.model.Task;

import javax.swing.*;
import java.awt.*;

public class TaskView extends JFrame {
    private Task task;
    private TaskController taskController;

    private JLabel nameLabel;
    private JTextArea descriptionArea;
    private JLabel dueDateLabel;
    private JLabel priorityLabel;
    private JLabel statusLabel;
    private JLabel tagsLabel;

    // Constructor
    public TaskView(Task task) {
        this.task = task;
        this.taskController = new TaskController();
        initUI();
    }

    // Method to initialize UI components
    private void initUI() {
        setTitle("Task Details");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(8, 2));

        add(new JLabel("Name:"));
        nameLabel = new JLabel(task.getName());
        add(nameLabel);

        add(new JLabel("Description:"));
        descriptionArea = new JTextArea(task.getDescription());
        descriptionArea.setEditable(false);
        add(new JScrollPane(descriptionArea));

        add(new JLabel("Due Date:"));
        dueDateLabel = new JLabel(task.getDueDate());
        add(dueDateLabel);

        add(new JLabel("Priority:"));
        priorityLabel = new JLabel(task.getPriority().toString());
        add(priorityLabel);

        add(new JLabel("Status:"));
        statusLabel = new JLabel(task.getStatus().toString());
        add(statusLabel);

        add(new JLabel("Tags:"));
        tagsLabel = new JLabel(String.join(", ", task.getTags()));
        add(tagsLabel);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> handleEdit());
        add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> handleDelete());
        add(deleteButton);
    }

    // Method to handle editing the task
    private void handleEdit() {
        JOptionPane.showMessageDialog(this, "Edit functionality not implemented yet.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to handle deleting the task
    private void handleDelete() {
        int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            taskController.deleteTask(task);
            JOptionPane.showMessageDialog(this, "Task deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
}
