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
        // Load the FXML UI definition
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/learningpath/ui/LearningPath.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600); // Set window size
        primaryStage.setTitle("Personalized Learning Path Generator");
        primaryStage.setScene(scene);
        primaryStage.show(); // Display the window
    }

    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}