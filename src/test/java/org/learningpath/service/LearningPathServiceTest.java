package org.learningpath.service;

import org.learningpath.model.Resource;
import org.learningpath.model.User;
import org.learningpath.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for LearningPathService using a mock ResourceRepository.
 */
class LearningPathServiceTest {
    private LearningPathService service;
    private ResourceRepository mockRepository;

    // Runs before each test to set up mock dependencies
    @BeforeEach
    void setUp() {
        mockRepository = new ResourceRepository() {
            @Override
            public List<Resource> getResourcesByDifficulty(String difficulty) {
                // Mock response: Always returns one test resource
                return Arrays.asList(
                        new Resource(1, "Test Tutorial", "https://example.com", difficulty, 5)
                );
            }
        };
        service = new LearningPathService(mockRepository);
    }

    // Tests if the service generates a path with the mock resource
    @Test
    void testGenerateLearningPath() {
        User user = new User("Learn Java", "Beginner", 10);
        List<Resource> path = service.generateLearningPath(user);
        assertEquals(1, path.size()); // Verify one resource is returned
        assertEquals("Test Tutorial", path.get(0).getTitle()); // Verify the mock title
    }
}