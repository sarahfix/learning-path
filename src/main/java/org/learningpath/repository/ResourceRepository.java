package org.learningpath.repository;
import org.learningpath.DanceCSVLoader;
import org.learningpath.model.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database operations for learning resources.
 * Uses SQLite to store and retrieve resource data.
 */
public class ResourceRepository {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/learningpath.db";

    // Constructor initializes the database schema and sample data
    public ResourceRepository() {
        initializeDatabase();
    }

    /**
     * Setting up and populating a data table with dance resource information.
     */
    private void initializeDatabase() {
        // Opening a connection to database
        try (Connection conn = DriverManager.getConnection(DB_URL);
             // Create Statement object
             Statement stmt = conn.createStatement()) {

            // Create fresh table
            stmt.execute("DROP TABLE IF EXISTS resources");
            // New resource table
            stmt.execute("CREATE TABLE resources (" +
                    "id INTEGER PRIMARY KEY, " +
                    "title TEXT NOT NULL, " +
                    "url TEXT NOT NULL, " +
                    "genre TEXT NOT NULL, " +
                    "difficulty TEXT NOT NULL, " +
                    "estimated_hours INTEGER NOT NULL)");

            // Load dance resources from CSV
            List<Resource> resources = DanceCSVLoader.loadFromCSV();
            // Adding each record to database
            for (Resource r : resources) {
                String sql = String.format(
                        "INSERT INTO resources VALUES (%d, '%s', '%s', '%s', '%s', %d)",
                        r.getId(),
                        r.getTitle().replace("'", "''"), // Handle apostrophes
                        r.getUrl(),
                        r.getGenre(),
                        r.getDifficulty(),
                        r.getEstimatedHours()
                );
                stmt.execute(sql);
            }
            // Confirmation
            System.out.println("Loaded " + resources.size() + " dance resources");

        } catch (Exception e) {
            System.err.println("Error initializing database:");
            e.printStackTrace();
        }
    }

    /**
     * Fetches resources matching a given difficulty level and genre.
     *
     * @param genre: "Ballet", "Jazz", or "Hip-Hop"
     * @param difficulty : "Beginner", "Intermediate", or "Advanced".
     * @return List of matching resources.
     */
    public List<Resource> getResourcesByGenreAndDifficulty(String genre, String difficulty) {
        // Initialize a new arraylist
        List<Resource> resources = new ArrayList<>();
        String sql = "SELECT * FROM resources WHERE genre = ? AND difficulty = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameters safely
            pstmt.setString(1, genre);
            pstmt.setString(2, difficulty);
            ResultSet rs = pstmt.executeQuery();
            // Loop through set and create Resource objects for each matching row.
            while (rs.next()) {
                resources.add(new Resource(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("url"),
                        rs.getString("genre"),
                        rs.getString("difficulty"),
                        rs.getInt("estimated_hours")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // List containing all matching entries
        return resources;
    }

    // Checks if the 'resources' table is empty
    private boolean isTableEmpty(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM resources");
        rs.next();
        return rs.getInt(1) == 0;
    }
}