Program Name: Game Of Life
Author: Eric Zhang
Date: December 2018
---Intro---
The program simulates a turn-based game that within a world, there are plants, herbivores, omnivores and carnivores.
The world is a 50x50 cells grid.
For each turn, lifeforms move one cell and eat food if encountering one. 
Also, lifeforms will give birth if certain condition is met.
The game is controlled by mouse clicking event, where each click triggers one turn.

---OOP Design---
Lifeform is an abstract class as super class.
It has children classes plants, herbivores, omnivores and carnivores.
Cell objects form a World object.
Cell and Lifeform objects reference each other.
World class is responsible for initializing and turning the game.
Main method launches the program.

---How to run/play---
The program creates a world shown in JavaFx gridpane.
Player can click anywhere on the gridpane to trigger game turns.
Observe the lifeforms.
