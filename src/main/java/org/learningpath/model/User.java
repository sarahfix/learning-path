package org.learningpath.model;

public class User {
    private String goal; // e.g., "Learn Java"
    private String skillLevel; // e.g., "Beginner", "Intermediate"
    private int weeklyHours; // Hours available per week

    public User(String goal, String skillLevel, int weeklyHours) {
        this.goal = goal;
        this.skillLevel = skillLevel;
        this.weeklyHours = weeklyHours;
    }

    public String getGoal() {
        return goal;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }
}
