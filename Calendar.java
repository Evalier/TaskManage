
//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class Calendar extends JFrame {

    private JComboBox<String> monthComboBox;
    private JPanel daysPanel;

    /**
     * Constructor initializes the Calendar components.
     */
    public Calendar() {
        setTitle("Calendar");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    /**
     * Sets up the layout and components of the calendar view.
     */
    private void initUI() {
        setLayout(new BorderLayout());

        // Month selection dropdown
        String[] months = {"January", "February", "March", "April", "May", "June",
                           "July", "August", "September", "October", "November", "December"};
        monthComboBox = new JComboBox<>(months);
        monthComboBox.addActionListener(new MonthComboBoxListener());

        // Panel to display days
        daysPanel = new JPanel(new GridLayout(0, 7)); // 7 columns for days of the week

        // Add components to the frame
        add(monthComboBox, BorderLayout.NORTH);
        add(daysPanel, BorderLayout.CENTER);

        // Initialize with the current month
        monthComboBox.setSelectedIndex(new GregorianCalendar().get(java.util.Calendar.MONTH));
        updateCalendar();
    }

    /**
     * Updates the calendar to display the days of the selected month.
     */
    private void updateCalendar() {
        daysPanel.removeAll();

        // Get the selected month and the number of days in that month
        int month = monthComboBox.getSelectedIndex();
        GregorianCalendar cal = new GregorianCalendar();
        int year = cal.get(java.util.Calendar.YEAR);
        cal.set(year, month, 1);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        // Add day labels (Sun, Mon, etc.)
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            daysPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        // Fill the calendar with empty labels for the days before the start of the month
        int firstDayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK) - 1;
        for (int i = 0; i < firstDayOfWeek; i++) {
            daysPanel.add(new JLabel());
        }

        // Add buttons for each day of the month
        for (int day = 1; day <= daysInMonth; day++) {
            JButton dayButton = new JButton(String.valueOf(day));
            dayButton.addActionListener(new DayButtonListener());
            daysPanel.add(dayButton);
        }

        // Refresh the panel
        daysPanel.revalidate();
        daysPanel.repaint();
    }

    private class MonthComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateCalendar();
        }
    }

    private class DayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            JOptionPane.showMessageDialog(Calendar.this, "Selected Day: " + source.getText());
        }
    }

    /**
     * Main method to run the Calendar.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calendar().setVisible(true);
        });
    }
}
