# Gravity Game

This repository contains the source code for "Gravity," a maze-based treasure collection game developed as a university project for the CME1252 Project Based Learning – II course at Dokuz Eylül University.

**Authors:**
* AHMED PATEL
* BARIS OLÇAY
* MUHARREM PEHLEVAN
* YASAMIN VALISHARIATPANAHI

---

## About The Project

"Gravity" is a 2D maze game played on a 25x55 field where the player's main objective is to achieve the highest score by collecting treasures. The player must navigate the maze, avoiding hostile computer-controlled robots and strategically dealing with boulders that are affected by gravity. The game features several dynamic elements, including a backpack for item management and an input queue that continuously adds new elements to the map, making each playthrough unique.

---

## Game Elements

* **P (Player)**: Controlled by the user with the arrow keys. Can push boulders and use a limited number of teleports.
* **X (Robot)**: Moves randomly in four directions. The game ends if a robot catches the player.
* **1, 2, 3 (Treasures)**: Collectible items that are added to the player's backpack to generate score and teleport rights.
* **O (Boulder)**: An obstacle affected by gravity. It will fall into empty spaces below it or slide sideways off other boulders. Can be pushed by the player.
* **: (Earth)**: Ground tiles that the player can walk on.
* **# (Wall)**: Impassable barriers that form the maze structure.

---

## Core Mechanics

* **Gravity System**: Boulders are the only element affected by gravity. They will fall downwards into empty spaces or slide to the side if on top of another boulder with an adjacent empty space. If a boulder falls on a robot, the robot is destroyed and the player gains 900 points.
* **Backpack**: The player has a stack-based backpack with a capacity of 8 items. When a treasure is collected, it's pushed onto the stack. If two identical treasure numbers are at the top of the stack, they are removed and converted into score points and, in the case of treasure '3', an additional teleport right.
* **Input Queue**: A circular queue constantly introduces new game elements (treasures, boulders, robots, etc.) onto the map at random locations every few seconds, ensuring the game world is always changing.
* **Teleportation**: The player starts with 3 teleport rights and can gain more by collecting '3' treasures. Pressing the spacebar teleports the player to a random empty square on the map.

---

## Screenshots

Here are some screenshots from the game, showing the start screen and a "Game Over" scenario.

![Start of Gravity Game](https://i.imgur.com/8Qj8nU6.png)
_The initial game screen, showing the player, robots, and treasures within the maze._

![Game Over Screen](https://i.imgur.com/c6c2V4w.png)
_The "Game Over" screen, triggered after a robot catches the player._

---

## Team Contributions

* **YASAMIN VALISHARIATPANAHI**: Implemented the robot's random movement algorithm and the game-over condition when a robot catches the player.
* **AHMED PATEL**: Created the start game screen, enabled robots to destroy treasures, and tested various game mechanics.
* **BARIŞ OLÇAY**: Developed the core gravity mechanics, including boulder falling and the player's ability to push boulders.
* **MUHARREM PEHLEVAN**: Designed the main game array for synchronization, implemented the input queue, and managed the logic for robot creation and destruction.

---

## How to Run

The project is built in Java and uses the Enigma Console library for text-based graphics.

1.  Make sure you have a Java Development Kit (JDK) installed.
2.  Include the Enigma library in your project's classpath.
3.  Compile all `.java` files.
4.  Run the `Main` class to start the game.

```bash
# Example compilation and run commands (may vary based on setup)
javac -cp "path/to/enigma.jar:." *.java
java -cp "path/to/enigma.jar:." Main
