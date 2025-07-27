package org.learningpath.ui;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller for the JavaFX UI (LearningPath.fxml).
 * Handles user input and displays the generated learning path.
 */
public class LearningPathController {
    @FXML private TextField goalField;          // Input: Learning goal (e.g., "Java")
    @FXML private ChoiceBox<String> skillLevelBox; // Dropdown: Skill level
    @FXML private TextField hoursField;        // Input: Weekly available hours
    @FXML private Button generateButton;       // Button: Triggers path generation
    @FXML private TextArea resultArea;         // Output: Displays the learning path

    // Initialize UI components (runs when FXML is loaded)
    @FXML
    private void initialize() {
        resultArea.setText("Enter your details and click 'Generate Learning Path'...");
    }

    // Called when the "Generate" button is clicked
    @FXML
    private void generateLearningPath() {
        String goal = goalField.getText();
        String skillLevel = skillLevelBox.getValue();
        String hours = hoursField.getText();
        // Placeholder logic (to be replaced with service calls)
        resultArea.setText("Goal: " + goal + "\nSkill Level: " + skillLevel + "\nHours: " + hours);
    }
}