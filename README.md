# Pong Game
## Overview
This project is my implementation of the classic game of pong in Java. This game gave me more rigorous exposure to developement of larger Java projects.

This is a simple Pong game implemented in Java using Swing for the graphical interface. The game consists of two paddles and a ball that bounce off the walls and paddles. Players control the paddles to prevent the ball from hitting the walls behind them.

## Files
Ball.java: Represents the game's ball object. It handles the ball's movement, collision detection with paddles, and scoring logic.

GameObject.java: An abstract class that serves as the base class for game objects such as paddles and the ball. It defines common properties and methods for game objects.

HUD.java: Manages the Heads-Up Display (HUD) for the game, displaying scores and other relevant information.

Main.java: The main entry point for the game. It sets up the game window, initializes game objects, manages game logic, and handles user input.

Paddle.java: Represents the paddles in the game. It handles paddle movement and collision detection with the ball.

Window.java: A JPanel subclass that renders the game graphics. It includes the game objects and HUD for display.
