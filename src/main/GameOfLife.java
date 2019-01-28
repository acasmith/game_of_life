package main;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Adam Smith
 * A class to represent the infinite board on which the game is played.
 *
 */

class GameOfLife
{
	public List<Cell> aliveCells = new ArrayList<>();
	
	public GameOfLife()
	{
		
	}

	
	/**
	 * Initialises the game board to a grid of dead cells with a specified dimensions.
	 * @param x the initial width of the board in cells.
	 * @param y the initial height of the board in cells.
	 * @return List<Cell> a list 
	 */
	private static Cell createInitialGrid(int x, int y)
	{
		//Create Cell
		//Fill neighbours with dead cells.
		//Send neighbour lists to neighbours.
		//Set aliveCells and store them in this.aliveCells
		
		
		Cell originalCell = new Cell();
		
		
		return originalCell;
	}
}
