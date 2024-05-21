import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;

class TaskCreator extends JFrame {

    public static Connection con;
    private JPanel mainPanel;
    private JTextField titleTextField;
    public JTextField descTextField;
    public JTextField pLevelField;
    public JTextField dueDateField;
    public JTextField categoryField;
    public JTextField reminderField;
    private JButton createTaskButton;
    private JLabel taskTitle;
    private JLabel nameTitle;
    private JLabel descripTitle;
    private JLabel levelTitle;
    private JLabel dueTitle;
    private JLabel categoryTitle;
    private JLabel remindTitle;

    public TaskCreator() {
        super("Task Creator");
        this.setContentPane(mainPanel);
        this.setSize(500, 400);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        taskTitle.setFont(new Font("Arial", Font.BOLD, 22));
        createTaskButton.setBackground(new Color(100, 181, 246)); // Soft Blue Color #64B5F6
        createTaskButton.setForeground(Color.WHITE);

        // Enhance button appearance with rounded borders and light gray border color
        createTaskButton.setFocusPainted(false);
        createTaskButton.setContentAreaFilled(false);
        createTaskButton.setOpaque(true);
        createTaskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createTaskButton.setFont(new Font("Arial", Font.BOLD, 24)); // Increased font size
        createTaskButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); // Make button bigger

        // Add a grey border to mainPanel, making it thicker
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 33));

    }

    public static void connect() {
        String url = "jdbc:mysql://localhost:3306/task";
        String userName = "root";
        String pass = "cs380";

        try {
            con = DriverManager.getConnection(url, userName, pass);
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println("exception" + e.getMessage());
        }

    }

    // Creates a task
    public void createTask() {
        String title = titleTextField.getText();
        String description = descTextField.getText();
        String priority = pLevelField.getText();
        String dueDate = dueDateField.getText();
        String category = categoryField.getText();
        String reminder = reminderField.getText();

        String query = "INSERT INTO tasks (title, description, priority, dueDate, category, reminder) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, title);
            pst.setString(2, description);
            pst.setString(3, priority);
            pst.setString(4, dueDate);
            pst.setString(5, category);
            pst.setString(6, reminder);
            pst.executeUpdate();
            System.out.println("Task Created Successfully");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


        titleTextField.setText("");
        descTextField.setText("");
        pLevelField.setText("");
        dueDateField.setText("");
        categoryField.setText("");
        reminderField.setText("");
    }

    public static void main(String[] args) {
        connect();
        new TaskCreator();
    }
}