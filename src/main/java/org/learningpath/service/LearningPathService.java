package org.learningpath.service;

import org.learningpath.model.Resource;
import org.learningpath.model.User;
import org.learningpath.repository.ResourceRepository;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Resource> generateDanceLearningPath(User user) {
        // 1. Fetch resources matching the user's skill level
        List<Resource> resources = resourceRepository.getResourcesByGenreAndDifficulty(
                user.getGoal(),  // e.g., "Ballet"
                user.getSkillLevel()
        );

        // 2. Sort by shortest duration first (to fit more limited time)
        resources.sort(Comparator.comparingInt(Resource::getEstimatedHours));

        // 3. Filter by available hours
        int totalHours = user.getWeeklyHours() * 12;
        List<Resource> path = new ArrayList<>();
        int currentHours = 0;

        for (Resource resource : resources) {
            if (currentHours + resource.getEstimatedHours() <= totalHours) {
                path.add(resource);
                currentHours += resource.getEstimatedHours();
            } else {
                break; // Stop if no more time
            }
        }
        return path;
    }
}