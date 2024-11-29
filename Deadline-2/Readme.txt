Angry Birds Clone - LibGDX Game

Project Overview

This is a LibGDX-based Angry Birds-style game featuring physics-based gameplay, multiple bird types, and destructible environments.

Features

- Physics-based bird launching mechanics
- Multiple bird types (Red and Yellow birds)
- Destructible wooden, glass, and steel blocks
- Pig targets to eliminate
- Slingshot mechanics with trajectory prediction
- Pause and game over screens
- Score tracking
- Save and load game functionality

Game Mechanics

Bird Launching
- Drag bird on slingshot to aim and launch
- Trajectory prediction shows potential bird path
- Limited number of birds per level (2 birds)

Scoring
- Destroy pigs: 100 points
- Destroy blocks: 50 points

Controls
- Drag to aim bird
- Release to launch
- `R` key: Reset level
- `Ctrl + S`: Save game
- `Ctrl + L`: Load game

Project Structure

Platforms
- `core`: Main game logic
- `lwjgl3`: Desktop platform launcher

Key Classes
- `Level1`: Primary game screen
- `Block`: Base class for destructible blocks
- `GameSaveManager`: Handles game state persistence
- `PauseScreen`, `WinScreen`, `LoseScreen`: Game state screens

Prerequisites

- Java Development Kit (JDK) 8+
- Gradle

Running the Game

Using Gradle
```bash
./gradlew lwjgl3:run
```

Build Instructions

Build Runnable JAR
```bash
./gradlew lwjgl3:jar
```

Dependencies
- LibGDX
- Java Swing (for UI components)

Future Improvements
- Additional levels
- More bird types
- Enhanced physics simulation
- Improved graphics and sound effects

 Contributors
Hitesh Bhat,Mandeep Singh Pruthi
