# Personalized Learning Path Generator
A Java application that generates personalized learning paths based on user goals, skill level, and available time.

## Setup
1. Install JDK 17[](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
2. Open in IntelliJ IDEA.
3. Ensure Maven downloads dependencies (sqlite-jdbc, JUnit).
4. Run `Main.java` to start the JavaFX app.

## Features
- Input learning goals, skill level, and weekly hours.
- Generates a learning path with curated resources.
- Stores resources in SQLite.

## Sample Output
- Welcome to the Personalized Learning Path Generator! Enter your learning goal (e.g., Learn Java): **Learn Java**
- Enter your skill level (Beginner/Intermediate/Advanced): **Beginner**
- Enter hours available per week: **5**
  - Your Personalized Learning Path:
  Java Basics Tutorial (Beginner, 5 hours): https://example.com/java-basics