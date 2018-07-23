# TIC TAC TOE 2.0

General Rules: https://en.wikipedia.org/wiki/Tic-tac-toe

Differently from the classic version the size of the playground is configurable between 3x3 and 10x10 as well as the player symbols (usually O and X).

Additionally, this version is for 3 players: the two users will play against each other and against the computer. Who is starting is random and in and output are on the console.
After each move, the new state of the playground is displayed and the player can enter the next position of their character one after another. The next position should be provided in a format like 3,2.


## How to play

Build the project with:

`` ./gradlew fatJar ``

And, from the main directory, execute:

`` java jar build/libs/tic-tac-toe-all-1.0.jar [ src/main/resources/games.properties ]``
