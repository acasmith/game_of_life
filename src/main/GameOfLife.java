package main;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Adam Smith
 * A class to represent the infinite board on which the game is played.
 * The object is effectively immutable. If you want a new game, make a new object.
 * This is because there's a synergy between starting a new game of life and a new GoL object.
 *
 */

class GameOfLife
{
	public List<Cell> aliveCells = new ArrayList<>();
	
	GameBoard aBoard;
	int numberOfIterations;
	
	/**
	 * Constructor
	 */
	public GameOfLife(int numberOfIterations, int[] aliveIndexes)
	{
		this.numberOfIterations = numberOfIterations;
		this.newGame(aliveIndexes);
	}
	
	/**
	 * Initiates a new Game of Life.
	 * @param aliveIndexes an array containing the integer indexes of the initial alive cells in the 3x3 starting grid.
	 */
	private void newGame(int[] aliveIndexes)
	{
		this.aBoard = new GameBoard(aliveIndexes);
		
	}

	/**
	 * Causes the next iteration in the game to occur by adding new live cells and killing others.
	 * Checks for the end states of no alive cells and maximum iterations.
	 */
	private void doIteration()
	{
		
	}
	
	public void printGameBoard()
	{
		this.aBoard.printGameBoard();
	}
}
