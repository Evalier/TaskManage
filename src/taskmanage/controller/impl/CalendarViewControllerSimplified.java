package taskmanage.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalendarViewControllerSimplified {

    @FXML
    private Label lblMonthYear;

    @FXML
    public void initialize() {
        // Simple initialization logic for testing in SceneBuilder
        lblMonthYear.setText("January 2024");
    }
}
