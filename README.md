# CSE116 Game Engine Project

Welcome to the Game Engine project developed as part of the CSE116 (Introduction to Computer Science II) course at the University at Buffalo. This project demonstrates core object-oriented programming concepts, pathfinding, and interactive game logic using Java.

## Overview

This game engine powers a simple 2D tile-based game featuring dynamic movement, enemy behavior, and player interaction within a grid-based world. The engine is designed to be modular, testable, and extensible — showcasing principles of inheritance, polymorphism, and encapsulation.

Key components include:
- A physics engine for movement and collision detection
- A flexible decision tree system for AI behaviors
- Breadth-first search (BFS) for pathfinding
- A rendering system for displaying the game world

## Game Features

-  **Player Entity**: Controlled via keyboard inputs (e.g., W/A/S/D).
-  **Enemies**: Powered by AI decision trees with smart pathfinding and obstacle avoidance.
-  **Pathfinding**: Enemies navigate using BFS to track the player while avoiding static obstacles.
-  **Collision Detection**: Entities can't pass through walls or other solid objects.
-  **Tile-Based World**: Game is played on a 2D grid with support for walls, paths, and entities.
-  **Decision Trees**: AI logic is abstracted into reusable decision components, enabling flexible enemy behaviors.

##  Technologies Used

- **Java 17+**
- **JUnit 5** for unit testing
- **Object-Oriented Programming**: Inheritance, Interfaces, Polymorphism
- **Data Structures**: Queues, Graphs, 2D Arrays

##  Project Structure

```bash
src/main/java/app
├── gameengine/       # Core game engine logic, decision tree classes, and interfaces
├── games/            # Platform walls, Game levels, Objects      
├── tests/            # JUnit test cases for each major module and startgame function
