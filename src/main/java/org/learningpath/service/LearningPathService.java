package org.learningpath.service;
import org.learningpath.model.Resource;
import org.learningpath.model.User;
import org.learningpath.repository.ResourceRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates personalized learning paths based on user preferences.
 * Uses ResourceRepository to fetch resources and applies filtering logic.
 */
public class LearningPathService {
    private final ResourceRepository resourceRepository;

    // Constructor: Dependency injection for ResourceRepository
    public LearningPathService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    /**
     * Generates a learning path for the user.
     * @param user : Contains goal, skill level, and weekly hours.
     * @return List of resources tailored to the user's constraints.
     */
    public List<Resource> generateLearningPath(User user) {
        List<Resource> path = new ArrayList<>();
        // Fetch resources matching the user's skill level
        List<Resource> resources = resourceRepository.getResourcesByDifficulty(user.getSkillLevel());

        // Calculate total available hours (12 weeks = ~3 months)
        int totalHours = user.getWeeklyHours() * 12;
        int currentHours = 0;

        // Add resources until the time budget is exhausted
        for (Resource resource : resources) {
            if (currentHours + resource.getEstimatedHours() <= totalHours) {
                path.add(resource);
                currentHours += resource.getEstimatedHours();
            }
        }
        return path;
    }
}