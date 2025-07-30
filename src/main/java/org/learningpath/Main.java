package org.learningpath;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main class to launch the JavaFX application.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            // Load the FXML UI definition
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/learningpath/ui/LearningPath.fxml"));

            // Set larger window size to accommodate dance content
            Scene scene = new Scene(fxmlLoader.load(), 500, 700);  // Increased from 400x600

            // Configure and show the stage
            primaryStage.setTitle("Dance Learning Path Generator");  // Updated title
            primaryStage.setScene(scene);

            // Set minimum window size
            primaryStage.setMinWidth(450);
            primaryStage.setMinHeight(650);


        primaryStage.show();
    } catch (IOException e) {
        // More detailed error handling
        System.err.println("Failed to load FXML file: " + e.getMessage());
        e.printStackTrace();

        // Fallback to console UI if FX fails
        System.out.println("Falling back to console interface...");
        new org.learningpath.ui.ConsoleUI().start();
    }
    }

    public static void main(String[] args) {
        // Check for command-line arguments to determine interface mode
        if (args.length > 0 && args[0].equalsIgnoreCase("--console")) {
            new org.learningpath.ui.ConsoleUI().start();
        } else {
            // Launch JavaFX application
            launch(args);
        }
    }
}