# gol
This project is only for study purpose.

The aim of this game is to predict the next generation based on the rules stated below and this is used in many mathematical courses for Computer science students Like Modules of Computations and Finite Automata.
You can measure your score by counting how many generations your initial pattern lives, and that is by clicking on “NEXT GENERATION” button on each phase and the score will be recorded, or you can click “AUTO GENERATE” and the game will go through all the phases automatically where you can check how long it stays alive. However, again the main game of this is to predict the next phase where the student can check his skills of analysis and prediction.

  Conway's Game of Life is a game invented by mathematician John Conway in 1970.
  Each cell lives in a square in a rectangular grid. A cell can either be dead or alive.
  Before you start the game, you need to provide an initial state. It involves advancing through time one step at a time.
  A cell's fate depends on the state of its 8 closest neighbors (the grid utilizes wrapping,
  meaning a cell on the far left is thought of as a neighbor of a cell on the far right,
  and the same principle applies at the top and bottom).
  We check each cell life or fate in the following phase based on these rules:
• Births: Each dead cell adjacent to exactly three live neighbors will become live in the next generation.
•	Death by isolation: Each live cell with one or fewer live neighbors will die in the next generation.
•	Death by overcrowding: Each live cell with four or more live neighbors will die in the next generation.
•	Survival: Each live cell with either two or three live neighbors will remain alive for the next generation
