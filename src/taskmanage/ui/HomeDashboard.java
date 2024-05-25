package taskmanage.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import taskmanage.controller.impl.TaskController;
import taskmanage.model.impl.Task;

public class HomeDashboard extends JFrame {
    private TaskController taskController;
    private JTable taskTable;
    private JLabel summaryLabel;

    public HomeDashboard() {
        taskController = new TaskController();
        initUI();
    }

    private void initUI() {
        setTitle("Home Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        summaryLabel = new JLabel("Task Summary");
        add(summaryLabel, BorderLayout.NORTH);

        taskTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(taskTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadTasks());
        add(refreshButton, BorderLayout.SOUTH);

        loadTasks();
    }

    private void loadTasks() {
        List<Task> tasks = taskController.fetchAllTasks();
        String[] columnNames = {"Name", "Description", "Due Date", "Priority", "Status", "Tags"};
        Object[][] data = new Object[tasks.size()][6];

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            data[i][0] = task.getName();
            data[i][1] = task.getDescription();
            data[i][2] = task.getDueDate();
            data[i][3] = task.getPriority();
            data[i][4] = task.getStatus();
            data[i][5] = String.join(", ", task.getTags());
        }

        taskTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            HomeDashboard ex = new HomeDashboard();
            ex.setVisible(true);
        });
    }
}
