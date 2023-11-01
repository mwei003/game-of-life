# game-of-life
A simple backend application for "Game of Life".

Send a post request to http://localhost:8080/states, providing the coordinates of the initial live cells in this format: [[row1,column1],[row2,column2],[row3,column3]...],
you will get the output of the coordinates of the live cells in the next 100 states.
Note that the size of the board is 200x200, so the coordinates must be in the range of 0 - 199 inclusive.
