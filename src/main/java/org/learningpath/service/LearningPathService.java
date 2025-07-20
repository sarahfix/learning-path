package org.learningpath.service;

import org.learningpath.model.Resource;
import org.learningpath.model.User;
import org.learningpath.repository.ResourceRepository;

import java.util.ArrayList;
import java.util.List;

public class LearningPathService {
    private final ResourceRepository resourceRepository;

    public LearningPathService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> generateLearningPath(User user) {
        // Simple algorithm: Select resources matching user's skill level
        List<Resource> path = new ArrayList<>();
        List<Resource> resources = resourceRepository.getResourcesByDifficulty(user.getSkillLevel());

        // Basic logic: Add resources if they fit within weekly hours
        int totalHours = user.getWeeklyHours() * 12; // Assume 3 months = 12 weeks
        int currentHours = 0;
        for (Resource resource : resources) {
            if (currentHours + resource.getEstimatedHours() <= totalHours) {
                path.add(resource);
                currentHours += resource.getEstimatedHours();
            }
        }
        return path;
    }
}