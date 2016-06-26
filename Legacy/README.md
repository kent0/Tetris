Tetris
======

This project is a Java implementation of Tetris. I have previously created a Java Tetris, but I decided to re-create a better version. No individual may use or modify any part of the code for profit.

## Requirement

This project can be compiled and run on most systems that are supported by Oracle to install the [Java SE Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). The Instructions to run Tetris is provided for users of Unix systems.

## Included Files

### Main.java

This is the main file which utilizes other files to run the Tetris game. The `main` function is implemented in this file.

### TPanel.java

This is an extension of JPanel. It mainly overrides the `paintComponent` method to draw the game. In addition, it stores a pointer to the same game pointed to by the main function.

### Game.java

This is the model of the Tetris game. Actions such as tetromino translation, rotation, line-clearing, score, line, level, and ending of the game are implemented.

### Tetromino.java

Tetromino is the nomenclature for the pieces used in Tetris. There are 7 types of tetrominos in the game of Tetris. This class provides translational and rotational movements of the tetrominos.

### Tetris.jar

A compiled archive of all the classes.

## Instructions

### Compilation

1. On a Unix system, put the files in the same folder.
2. `cd` into the folder.
3. Run the command `$ javac *.java`
4. Run the command `$ java Main`

### Game Control

- 'a', 's', and 'd' keys for translational movement.
- 'w' key for hard drop.
- 'j' & 'k' keys for rotational movement.
- 'l' key for hold.
