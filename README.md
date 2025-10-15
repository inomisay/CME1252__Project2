# Gravity Game

[cite_start]This repository contains the source code for "Gravity," a maze-based treasure collection game developed as a university project for the **CME1252 Project Based Learning – II** course at Dokuz Eylül University[cite: 119, 122].

**Authors:**
* [cite_start]AHMED PATEL [cite: 124]
* [cite_start]BARIS OLÇAY [cite: 125]
* [cite_start]MUHARREM PEHLEVAN [cite: 126]
* [cite_start]YASAMIN VALISHARIATPANAHI [cite: 127]

---

## About The Project

[cite_start]"Gravity" is a 2D maze game played on a 25x55 field where the player's main objective is to achieve the highest score by collecting treasures[cite: 122]. The player must navigate the maze, avoiding hostile computer-controlled robots and strategically dealing with boulders that are affected by gravity. The game features several dynamic elements, including a backpack for item management and an input queue that continuously adds new elements to the map, making each playthrough unique.

### Game Elements

* **P (Player)**: Controlled by the user with the arrow keys. [cite_start]Can push boulders and use a limited number of teleports[cite: 179, 180, 182].
* **X (Robot)**: Moves randomly in four directions. [cite_start]The game ends if a robot catches the player[cite: 138, 194, 195].
* [cite_start]**1, 2, 3 (Treasures)**: Collectible items that are added to the player's backpack to generate score and teleport rights[cite: 183].
* **O (Boulder)**: An obstacle affected by gravity. It will fall into empty spaces below it or slide sideways off other boulders. [cite_start]Can be pushed by the player[cite: 142].
* [cite_start]**: (Earth)**: Ground tiles that the player can walk on[cite: 157].
* [cite_start]**# (Wall)**: Impassable barriers that form the maze structure[cite: 157].

### Core Mechanics

* **Gravity System**: Boulders are the only element affected by gravity. [cite_start]They will fall downwards into empty spaces or slide to the side if on top of another boulder with an adjacent empty space[cite: 142]. [cite_start]If a boulder falls on a robot, the robot is destroyed and the player gains 900 points[cite: 161].
* **Backpack**: The player has a stack-based backpack with a capacity of 8 items. When a treasure is collected, it's pushed onto the stack. [cite_start]If two identical treasure numbers are at the top of the stack, they are removed and converted into score points and, in the case of treasure '3', an additional teleport right[cite: 185].
* [cite_start]**Input Queue**: A circular queue constantly introduces new game elements (treasures, boulders, robots, etc.) onto the map at random locations every few seconds, ensuring the game world is always changing[cite: 145, 158].
* **Teleportation**: The player starts with 3 teleport rights and can gain more by collecting '3' treasures. [cite_start]Pressing the spacebar teleports the player to a random empty square on the map[cite: 166, 182].

---

## Screenshots

Here are some screenshots from the game, showing the start screen and a "Game Over" scenario.

![Start of Gravity Game](https://i.imgur.com/8Qj8nU6.png)
_The initial game screen, showing the player, robots, and treasures within the maze._

![Game Over Screen](https://i.imgur.com/c6c2V4w.png)
_The "Game Over" screen, triggered after a robot catches the player._

---

## Team Contributions

* [cite_start]**YASAMIN VALISHARIATPANAHI**: Implemented the robot's random movement algorithm and the game-over condition when a robot catches the player[cite: 138, 222].
* [cite_start]**AHMED PATEL**: Created the start game screen, enabled robots to destroy treasures, and tested various game mechanics[cite: 140].
* [cite_start]**BARIŞ OLÇAY**: Developed the core gravity mechanics, including boulder falling and the player's ability to push boulders[cite: 142].
* [cite_start]**MUHARREM PEHLEVAN**: Designed the main game array for synchronization, implemented the input queue, and managed the logic for robot creation and destruction[cite: 145].

---

## How to Run

[cite_start]The project is built in Java and uses the **Enigma Console library** for text-based graphics[cite: 249, 381].

1.  Make sure you have a Java Development Kit (JDK) installed.
2.  Include the Enigma library in your project's classpath.
3.  Compile all `.java` files.
4.  Run the `Main` class to start the game.

```bash
# Example compilation and run commands (may vary based on setup)
javac -cp "path/to/enigma.jar:." *.java
java -cp "path/to/enigma.jar:." Main
