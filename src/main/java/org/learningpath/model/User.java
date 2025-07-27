package org.learningpath.model;

/**
 * Represents a user's learning preferences and constraints.
 * Used to generate personalized learning paths.
 */
public class User {
    private String goal;          // Learning objective (e.g., "Learn Java")
    private String skillLevel;    // Proficiency: "Beginner", "Intermediate", "Advanced"
    private int weeklyHours;      // Hours the user can dedicate per week

    // Constructor to initialize all fields
    public User(String goal, String skillLevel, int weeklyHours) {
        this.goal = goal;
        this.skillLevel = skillLevel;
        this.weeklyHours = weeklyHours;
    }

    // Getters for all fields (no setters, as this is an immutable model)
    public String getGoal() { return goal; }
    public String getSkillLevel() { return skillLevel; }
    public int getWeeklyHours() { return weeklyHours; }
}
