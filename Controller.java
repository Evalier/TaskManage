import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame {
    private JButton openCalendarButton;
    private JButton closeCalendarButton;
    private Calendar calendar;

    /**
     * Constructor initializes the Controller components.
     */
    public Controller() {
        setTitle("Controller");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    /**
     * Sets up the layout and components of the controller view.
     */
    private void initUI() {
        JPanel panel = new JPanel();
        openCalendarButton = new JButton("Open Calendar");
        closeCalendarButton = new JButton("Close Calendar");

        openCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (calendar == null || !calendar.isVisible()) {
                    calendar = new Calendar();
                    calendar.setVisible(true);
                }
            }
        });

        closeCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (calendar != null && calendar.isVisible()) {
                    calendar.dispose();
                }
            }
        });

        panel.add(openCalendarButton);
        panel.add(closeCalendarButton);

        add(panel);
    }

    /**
     * Main method to run the Controller.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Controller().setVisible(true);
        });
    }
}
