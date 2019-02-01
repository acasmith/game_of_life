package main;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Adam
 * A class to represent the infinite board on which the game is played. 
 */

public class GameBoard
{
	List<Cell> aliveCells;
	
	/**
	 * Constructor
	 */
	public GameBoard(int[] aliveIndexes)
	{
		this.aliveCells = this.createInitialGrid(aliveIndexes);
	}
	
	/*
	 * Initialises the game board to a 3x3 grid of cells.
	 * Separate from constructor in case any other logic needs to be added to constructor.
	 */
	private List<Cell> createInitialGrid(int[] aliveIndexes)
	{
		//Create Cell
		Cell originCell = new Cell();
		
		//Set cell as the origin of the gameboard.
		originCell.makeOriginCell();
		
		//Create list for aliveCells. Locally scoped, NOT the instance variable.
		List<Cell> aliveCells = new ArrayList<>();
		
		//Set aliveCells and store them in this.aliveCells
		for(int i = 0; i < aliveIndexes.length; i ++)
		{
			Cell bornCell = originCell.neighbours[aliveIndexes[i]];
			bornCell.doBirth();
			aliveCells.add(bornCell);
		}
		
		return aliveCells;
	}
}
