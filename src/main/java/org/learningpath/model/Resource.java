package org.learningpath.model;

public class Resource {
    private int id;
    private String title; // e.g., "Java Basics Tutorial"
    private String url; // e.g., "https://example.com/java-basics"
    private String difficulty; // e.g., "Beginner"
    private int estimatedHours; // Time to complete

    public Resource(int id, String title, String url, String difficulty, int estimatedHours) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.difficulty = difficulty;
        this.estimatedHours = estimatedHours;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }
}
