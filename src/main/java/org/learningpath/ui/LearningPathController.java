package org.learningpath.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LearningPathController {
    @FXML
    private TextField goalField;
    @FXML
    private ChoiceBox<String> skillLevelBox;
    @FXML
    private TextField hoursField;
    @FXML
    private Button generateButton;
    @FXML
    private TextArea resultArea;

    @FXML
    private void initialize() {
        // Initialize any default settings
        resultArea.setText("Enter your details and click 'Generate Learning Path' to see your personalized path.");
    }

    @FXML
    private void generateLearningPath() {
        // Temporary placeholder to test the GUI
        String goal = goalField.getText();
        String skillLevel = skillLevelBox.getValue();
        String hours = hoursField.getText();
        resultArea.setText("Goal: " + goal + "\nSkill Level: " + (skillLevel != null ? skillLevel : "Not selected") + "\nHours: " + hours);
    }
}