package org.learningpath.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.learningpath.model.Resource;
import org.learningpath.model.User;
import org.learningpath.repository.ResourceRepository;
import org.learningpath.service.LearningPathService;

import java.util.List;

public class LearningPathController {
    @FXML private ComboBox<String> genreBox;
    @FXML private ComboBox<String> skillLevelBox;
    @FXML private TextField hoursField;
    @FXML private TextArea resultArea;

    @FXML
    private void initialize() {
        // Set default values
        genreBox.getSelectionModel().selectFirst();
        skillLevelBox.getSelectionModel().selectFirst();
        resultArea.setText("Select your dance preferences and click 'Generate My Dance Path'");
    }

    @FXML
    private void generateLearningPath() {
        try {
            // Get values from JavaFX UI
            String genre = genreBox.getValue();
            String skillLevel = skillLevelBox.getValue();
            String hoursText = hoursField.getText();

            // Validate inputs using helper method
            if (!validateInputs(genre, skillLevel, hoursText)) {
                return;
            }
            // Validating numeric input
            int weeklyHours;
            try {
                weeklyHours = Integer.parseInt(hoursText);
            } catch (NumberFormatException e) {
                resultArea.setText("Please enter valid hours (e.g., 5)");
                return;
            }
            // Generate and display learning path
            User user = new User(genre, skillLevel, weeklyHours);
            // Generate tailored list of Resource objects
            LearningPathService service = new LearningPathService(new ResourceRepository());
            List<Resource> path = service.generateDanceLearningPath(user);

            // Display results using helper method
            displayLearningPath(path, weeklyHours);

        } catch (Exception e) {
            resultArea.setText("Error generating your dance path. Please try again.");
            e.printStackTrace();
        }
    }

    /**
     * Helper method to format and display the generated learning path in a readable way.
     * @param path List<Resource>
     * @param weeklyHours Input hours
     */
    private void displayLearningPath(List<Resource> path, int weeklyHours) {
        // Build the output string
        StringBuilder resultBuilder = new StringBuilder();
        if (path.isEmpty()) {
            resultBuilder.append("No resources found for your selected criteria.");
        } else {
            // Builds a formatted string with each resource's
            // title, difficulty, duration, and URL
            resultBuilder.append("=== Your Dance Learning Path ===\n\n");
            int totalHours = 0;

            for (Resource resource : path) {
                resultBuilder.append(String.format(
                        "» %s\n   - Level: %s\n   - Duration: %d hours\n   - Video: %s\n\n",
                        resource.getTitle(),
                        resource.getDifficulty(),
                        resource.getEstimatedHours(),
                        resource.getUrl()
                ));
                totalHours += resource.getEstimatedHours();
            }
            // Calculate hours and estimated weeks
            resultBuilder.append(String.format(
                    "Total program duration: %d hours (≈%d weeks at %d hrs/week)",
                    totalHours,
                    (int) Math.ceil((double)totalHours/weeklyHours),
                    weeklyHours
            ));
        }
        resultArea.setText(resultBuilder.toString());
    }

    /**
     * Helper method to validate inputs
     */
    private boolean validateInputs(String genre, String level, String hours) {
        // Null check
        if (genre == null || level == null || hours.isEmpty()) {
            showError("Please fill in all fields");
            return false;
        }
        // Numeric validation
        try {
            int h = Integer.parseInt(hours);
            if (h <= 0 || h > 5) {
                showError("Please enter valid hours between 1 and 5");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Hours must be a whole number");
            return false;
        }
        return true;
    }

    /**
     * Displays user-friendly error messages.
     * @param message Error message
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
