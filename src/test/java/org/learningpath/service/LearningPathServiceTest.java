package org.learningpath.service;

import org.learningpath.model.Resource;
import org.learningpath.model.User;
import org.learningpath.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class LearningPathServiceTest {
    private LearningPathService service;
    private ResourceRepository mockRepository;

    @BeforeEach
    void setUp() {
        mockRepository = new ResourceRepository() {
            @Override
            public List<Resource> getResourcesByDifficulty(String difficulty) {
                return Arrays.asList(
                        new Resource(1, "Test Tutorial", "https://example.com", difficulty, 5)
                );
            }
        };
        service = new LearningPathService(mockRepository);
    }

    @Test
    void testGenerateLearningPath() {
        User user = new User("Learn Java", "Beginner", 10);
        List<Resource> path = service.generateLearningPath(user);
        assertEquals(1, path.size());
        assertEquals("Test Tutorial", path.get(0).getTitle());
    }
}
