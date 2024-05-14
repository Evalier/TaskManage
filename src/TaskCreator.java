import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

class TaskCreator extends JFrame {

    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
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
        createTaskButton.setBorder(new RoundedBorder(20, Color.LIGHT_GRAY)); // Added Color.LIGHT_GRAY for border
        createTaskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createTaskButton.setFont(new Font("Arial", Font.BOLD, 12));

        // Add a grey border to mainPanel, making it thicker
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 30));
    }

    private static class RoundedBorder implements Border {
        private int radius;
        private Color borderColor;

        RoundedBorder(int radius, Color borderColor) {
            this.radius = radius;
            this.borderColor = borderColor;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius, this.radius, this.radius, this.radius);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(borderColor);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    public static void main(String[] args) {
        new TaskCreator();
    }
}