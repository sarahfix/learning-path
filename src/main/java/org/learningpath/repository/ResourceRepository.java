package org.learningpath.repository;
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
     * Creates the 'resources' table if it doesn't exist and inserts sample data.
     */
    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            // Create table
            String sql = "CREATE TABLE IF NOT EXISTS resources (" +
                    "id INTEGER PRIMARY KEY, " +
                    "title TEXT NOT NULL, " +
                    "url TEXT NOT NULL, " +
                    "difficulty TEXT NOT NULL, " +
                    "estimated_hours INTEGER NOT NULL)";
            stmt.execute(sql);

            // Insert sample data if the table is empty
            if (isTableEmpty(stmt)) {
                String insertSql = "INSERT INTO resources VALUES " +
                        "(1, 'Java Basics Tutorial', 'https://example.com/java-basics', 'Beginner', 5)," +
                        "(2, 'Java OOP Concepts', 'https://example.com/java-oop', 'Intermediate', 8)," +
                        "(3, 'Java Variables and Types', 'https://example.com/java-variables', 'Beginner', 3)," +
                        "(4, 'Java Advanced Patterns', 'https://example.com/java-patterns', 'Advanced', 10)";
                stmt.execute(insertSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Checks if the 'resources' table is empty
    private boolean isTableEmpty(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM resources");
        rs.next();
        return rs.getInt(1) == 0;
    }

    /**
     * Fetches resources matching a given difficulty level.
     * @param difficulty : "Beginner", "Intermediate", or "Advanced".
     * @return List of matching resources.
     */
    public List<Resource> getResourcesByDifficulty(String difficulty) {
        List<Resource> resources = new ArrayList<>();
        String sql = "SELECT * FROM resources WHERE difficulty = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, difficulty);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resources.add(new Resource(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("url"),
                        rs.getString("difficulty"),
                        rs.getInt("estimated_hours")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resources;
    }
}