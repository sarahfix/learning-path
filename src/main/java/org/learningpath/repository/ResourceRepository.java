package org.learningpath.repository;
import org.learningpath.model.Resource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ResourceRepository {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/learningpath.db";

    public ResourceRepository() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS resources (" +
                    "id INTEGER PRIMARY KEY, " +
                    "title TEXT NOT NULL, " +
                    "url TEXT NOT NULL, " +
                    "difficulty TEXT NOT NULL, " +
                    "estimated_hours INTEGER NOT NULL)";
            stmt.execute(sql);

            // Insert sample data if table is empty
            String countSql = "SELECT COUNT(*) FROM resources";
            ResultSet rs = stmt.executeQuery(countSql);
            rs.next();
            if (rs.getInt(1) == 0) {
                String insertSql = "INSERT INTO resources (id, title, url, difficulty, estimated_hours) VALUES " +
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
