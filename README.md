<div align="center">

# 🐍 Snake Game

### A classic desktop Snake game built with Java Swing

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Java Swing](https://img.shields.io/badge/Java%20Swing-007396?style=for-the-badge&logo=java&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Object--Oriented-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

</div>

---

## 📖 Overview

Snake Game is a desktop application developed with Java Swing.

The player controls a snake, collects apples to increase the score, and tries to avoid hitting the walls or the snake's own body. The project demonstrates object-oriented programming, event handling, collision detection, keyboard input, and custom GUI rendering.

---

## ✨ Features

- 🎮 Real-time Snake gameplay
- 🍎 Random apple generation
- 📈 Automatic snake growth
- 🏆 Live score display
- 💥 Wall and self-collision detection
- ⌨️ Arrow-key controls
- 🔄 Reset and Play Again options
- 🚫 Prevention of instant reverse movement
- 🎨 Custom Swing graphics and animations
- 🖥️ Game-over screen with final score

---

## 🎮 Controls

| Action | Key |
|---|---|
| Move Up | `↑` |
| Move Down | `↓` |
| Move Left | `←` |
| Move Right | `→` |
| Restart | Reset / Play Again button |

---

## 🛠️ Technologies

- Java
- Java Swing
- Java AWT
- Object-Oriented Programming
- Event-driven programming
- Swing Timer
- Custom graphics rendering

---

## 📁 Project Structure

```text
snakeGame/
├── snakeGame/
│   ├── src/
│   │   ├── module-info.java
│   │   └── snakeGame/
│   │       ├── GamePanel.java
│   │       ├── gameFrame.java
│   │       └── snakeGame.java
│   └── bin/
└── README.md
```

### Main Classes

- `snakeGame.java` — application entry point
- `gameFrame.java` — creates and configures the game window
- `GamePanel.java` — handles gameplay, controls, drawing, scoring, and collision detection

---

## 🚀 Getting Started

### Prerequisites

Install Java JDK 11 or later.

Check your Java installation:

```bash
java -version
```

### Installation

1. Clone the repository:

```bash
git clone https://github.com/eyadHaddad98/snakeGame.git
```

2. Open the project in IntelliJ IDEA, Eclipse, or another Java IDE.

3. Open:

```text
snakeGame/src/snakeGame/snakeGame.java
```

4. Run the `snakeGame` class.

5. Use the arrow keys to control the snake.

---

## 🧠 How the Game Works

1. The snake moves continuously across the game board.
2. The player changes its direction using the arrow keys.
3. Eating an apple increases the score and extends the snake.
4. A new apple appears in a random position.
5. The game ends if the snake hits a wall or its own body.
6. The player can restart using the Reset or Play Again button.

---

## 🔮 Possible Future Improvements

- Multiple difficulty levels
- Pause and resume functionality
- Persistent high-score system
- Sound effects and background music
- Different board themes
- Additional food and bonus items
- Automated unit tests

---

<div align="center">


</div>
