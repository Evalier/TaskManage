import javax.swing.*;
import java.awt.*;

public class TaskView extends JFrame {
    private JButton editButton;
    private JButton deleteButton;
    private JLabel taskManageTitle;
    private JPanel mainPanel;
    private JLabel taskViewTitle;
    private JScrollPane taskManPane;
    private JButton finishEditButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;

    public TaskView() {
        super("Task Creator");
        this.setContentPane(mainPanel);
        this.setSize(500, 400);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        taskViewTitle.setFont(new Font("Arial", Font.BOLD, 22));

        // Customize buttons
        customizeButton(editButton);
        customizeButton(deleteButton);
        customizeButton(finishEditButton);

        // Set light grey thick border for mainPanel
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 30));
    }

    private void customizeButton(JButton button) {
        button.setBackground(new Color(100, 181, 246)); // Color 100, 181, 246
        button.setForeground(Color.WHITE); // Button text color white
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80)); // Make button bigger
        button.setFont(new Font("Arial", Font.BOLD, 18)); // Slightly increased font size

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(135, 206, 250)); // Slightly darker blue
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 181, 246)); // Color 100, 181, 246
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Even darker blue for press
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(135, 206, 250)); // Slightly darker blue
            }
        });

        button.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2, true));
    }

    public static void main(String[] args) {
        new TaskView();
    }
}