package org.learningpath.ui;

import org.learningpath.model.User;
import org.learningpath.model.Resource;
import org.learningpath.service.LearningPathService;
import org.learningpath.repository.ResourceRepository;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final LearningPathService learningPathService;
    private final Scanner scanner;

    public ConsoleUI() {
        this.learningPathService = new LearningPathService(new ResourceRepository());
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Personalized Learning Path Generator!");
        System.out.print("Enter your learning goal (e.g., Learn Java): ");
        String goal = scanner.nextLine();
        System.out.print("Enter your skill level (Beginner/Intermediate/Advanced): ");
        String skillLevel = scanner.nextLine();
        System.out.print("Enter hours available per week: ");
        int weeklyHours = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        User user = new User(goal, skillLevel, weeklyHours);
        List<Resource> learningPath = learningPathService.generateLearningPath(user);

        System.out.println("\nYour Personalized Learning Path:");
        if (learningPath.isEmpty()) {
            System.out.println("No resources found for your skill level.");
        } else {
            for (Resource resource : learningPath) {
                System.out.printf("- %s (%s, %d hours): %s\n",
                        resource.getTitle(), resource.getDifficulty(), resource.getEstimatedHours(), resource.getUrl());
            }
        }
    }
}
