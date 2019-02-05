# Game of Life
Game of Life command line app implemented in Java.

## Playing the game
Pass in a string array to the main method where:
* Index 0 is a positive integer representing the number of iterations for the game to execute before stopping.
* Optional: any other array elements represent the positive integer indexes of the cells that are alive on the initial 3x3 board, from 0-8 inclusive. The notation has 0 in the top left corner moving right to left, ending with 8 in the bottom right.

### Output and Notation
* The output is restricted to the smallest matrix that can contain all of the current alive cells in the game.
* The output of every turn is printed to the console.
* Above and along the sides are matrix indexes which scale to the size of the grid.
* The origin of the grid, the original cell, is always plotted. When the original cell is alive, it's represented as a `0`. When the original cell is dead, it's represented as a `.`.
* All other alive cells are represented as an `A`
* Any dead cells present on the viewable grid are represented as an ` ` (single whitespace character).

### Example
Passing in the string array ["1", "2", "4"] gives the output:
```
Initial board:
  0 1
0| |A|
1|0| |

Turn 1:
All cells are dead.

Game ended: all cells are dead.
```

## Assumptions:
* There’s no specific instruction regarding end product or output.  
Assume a working version of GoL that implements the ruleset in the file and outputs the final result after the game ends.  
* No specification for the creation of an initial seed grid given.  
Assume an initial 3x3 grid for simplicity.  
* No specification of when to stop iterations.  
Assume some parameter to provide a number of iterations to perform.  
Assume to end the game when there are no live cells.
* No specification of domain the program is intended to be used in.  
Assume some kind of human user, so ability to pass in arguments from the command line and also view output.
