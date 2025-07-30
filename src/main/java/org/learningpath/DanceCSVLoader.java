package org.learningpath;

import org.learningpath.model.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for loading dance resources from a CSV file
 * and converting them into a list of Resource objects
 */
public class DanceCSVLoader {
    public static List<Resource> loadFromCSV() throws IOException {
        // Initialize a new arraylist
        List<Resource> resources = new ArrayList<>();

        // Read from csv
        try (InputStream is = DanceCSVLoader.class.getResourceAsStream("/dance_resources.csv");
             // Line-by-line reading
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            // Skip header
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    // Split each subsequent line by commas
                    String[] parts = line.split(",");
                    // Ensure each line has exactly 6 fields (Resource attributes)
                    if (parts.length != 6) {
                        System.err.println("Skipping malformed line: " + line);
                        continue;
                    }
                    // id (integer), title (string), url (string), genre (string),
                    // difficulty (string), estimated_hours (integer)
                    resources.add(new Resource(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            Integer.parseInt(parts[5].trim())
                    ));
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        }
        // Returns a List<Resource> containing all valid entries from csv
        return resources;
    }
}
