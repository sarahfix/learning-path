package org.learningpath.model;

/**
 * Represents a learning resource (e.g., tutorial, course, book).
 * Stores metadata like title, URL, difficulty level, and estimated completion time.
 */
public class Resource {
    private int id;                 // Unique identifier for the resource
    private String title;           // Name of the resource (e.g., "Java Basics Tutorial")
    private String url;             // Link to access the resource
    private String difficulty;      // Difficulty level: "Beginner", "Intermediate", "Advanced"
    private int estimatedHours;     // Estimated hours needed to complete the resource

    // Constructor to initialize all fields
    public Resource(int id, String title, String url, String difficulty, int estimatedHours) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.difficulty = difficulty;
        this.estimatedHours = estimatedHours;
    }

    // Getters for all fields (no setters, as this is an immutable model)
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getUrl() { return url; }
    public String getDifficulty() { return difficulty; }
    public int getEstimatedHours() { return estimatedHours; }
}
