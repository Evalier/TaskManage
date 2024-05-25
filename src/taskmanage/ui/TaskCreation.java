package taskmanage.ui;

import taskmanage.controller.impl.TaskController;
import taskmanage.constants.EnumsAndConstants.PriorityLevel;
import taskmanage.model.impl.Task;
import taskmanage.utility.impl.DataValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class TaskCreation extends JFrame {
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextField dueDateField;
    private JComboBox<PriorityLevel> priorityComboBox;
    private JTextField tagsField;
    private TaskController taskController;

    // Constructor
    public TaskCreation() {
        taskController = new TaskController();
        initUI();
    }

    // Method to initialize UI components
    private void initUI() {
        setTitle("Create New Task");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(7, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Description:"));
        descriptionArea = new JTextArea();
        add(new JScrollPane(descriptionArea));

        add(new JLabel("Due Date (YYYY-MM-DD):"));
        dueDateField = new JTextField();
        add(dueDateField);

        add(new JLabel("Priority:"));
        priorityComboBox = new JComboBox<>(PriorityLevel.values());
        add(priorityComboBox);

        add(new JLabel("Tags (comma-separated):"));
        tagsField = new JTextField();
        add(tagsField);

        JButton createButton = new JButton("Create Task");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTask();
            }
        });
        add(createButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton);
    }

    // Method to create a new task
    private void createTask() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        String dueDate = dueDateField.getText();
        PriorityLevel priority = (PriorityLevel) priorityComboBox.getSelectedItem();
        String[] tagsArray = tagsField.getText().split(",");
        HashSet<String> tags = new HashSet<>(Arrays.asList(tagsArray));

        // Validate inputs
      //  if (!taskmanage.utility.DataValidator.validateString(name)) {
      //      JOptionPane.showMessageDialog(this, "Invalid input. Please check your data.", "Error", JOptionPane.ERROR_MESSAGE);
        //    return;
      //  } else if (!taskmanage.utility.DataValidator.validateString(description) || !taskmanage.utility.DataValidator.validateDate(dueDate) || !tags.stream().allMatch(taskmanage.utility.DataValidator::validateTag)) {
      //      JOptionPane.showMessageDialog(this, "Invalid input. Please check your data.", "Error", JOptionPane.ERROR_MESSAGE);
      //      return;
      //  }

        // Create and add task
        Task task = new Task(name, description, dueDate, priority);
        for (String tag : tags) {
            task.addTag(tag.trim());
        }
        taskController.addTask(task);
        JOptionPane.showMessageDialog(this, "Task created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
