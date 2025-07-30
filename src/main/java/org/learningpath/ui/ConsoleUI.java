package org.learningpath.ui;

import org.learningpath.model.User;
import org.learningpath.model.Resource;
import org.learningpath.service.LearningPathService;
import org.learningpath.repository.ResourceRepository;

import java.util.List;
import java.util.Scanner;

/**
 * This class provides a text-based interface for generating dance learning paths.
 * It interacts with users via console input/output and coordinates with
 * the LearningPathService to generate personalized recommendations.
 */
public class ConsoleUI {
    private final LearningPathService learningPathService;
    private final Scanner scanner;

    // Initializing dependencies
    public ConsoleUI() {
        this.learningPathService = new LearningPathService(new ResourceRepository());
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Dance Learning Path Generator ===");
        System.out.print("Available dance styles: Ballet, Hip-Hop, Pointe, Contemporary");

        // Get dance style input
        System.out.print("Enter the dance style you want to learn: ");
        String danceStyle = scanner.nextLine();

        // Get skill level input with validation
        String skillLevel;
        while (true) {
            System.out.print("Enter your skill level (Beginner/Intermediate/Advanced): ");
            skillLevel = scanner.nextLine();
            if (skillLevel.equalsIgnoreCase("Beginner") ||
                    skillLevel.equalsIgnoreCase("Intermediate") ||
                    skillLevel.equalsIgnoreCase("Advanced")) {
                break;
            }
            System.out.println("Invalid input. Please enter Beginner, Intermediate, or Advanced.");
        }

        // Get weekly hours with validation
        int weeklyHours = 0;
        while (weeklyHours <= 0) {
            System.out.print("Enter hours available per week: ");
            try {
                weeklyHours = Integer.parseInt(scanner.nextLine());
                if (weeklyHours <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Create user and generate path
        User user = new User(danceStyle, skillLevel, weeklyHours);
        List<Resource> learningPath = learningPathService.generateDanceLearningPath(user);

        // Display results
        System.out.println("\n=== Your Personalized Dance Learning Path ===");
        if (learningPath.isEmpty()) {
            System.out.println("No resources found matching your criteria.");
        } else {
            System.out.printf("For %s %s (Practicing %d hours/week):\n\n",
                    skillLevel, danceStyle, weeklyHours);

            int totalHours = 0;
            for (Resource resource : learningPath) {
                System.out.printf("» %s\n   - Level: %s\n   - Duration: %d hours\n   - Video: %s\n\n",
                        resource.getTitle(),
                        resource.getDifficulty(),
                        resource.getEstimatedHours(),
                        resource.getUrl());
                totalHours += resource.getEstimatedHours();
            }
            System.out.printf("Total program duration: %d hours (approx. %d weeks)\n",
                    totalHours, (int) Math.ceil((double)totalHours/weeklyHours));
        }

        System.out.println("\nHappy dancing!");
    }
}