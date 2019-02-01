package main;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	/**
	 * Method to print out the current status of the game board.
	 * Limits of what is displayed are set by the alive cells positioned farthest from the origin (0, 0).
	 */
	public void printGameBoard()
	{
		String result = "All cells are dead.";
		
		if(!this.aliveCells.isEmpty())
		{
			int minX = 0;
			int maxX = 0;
			int minY = 0;
			int maxY = 0;
			
			//Iterate over aliveCells to find x and y boundaries of board.
			for(Cell aliveCell : this.aliveCells)
			{
				int x = aliveCell.getX();
				int y = aliveCell.getY();
				
				minX = x < minX ? x : minX;
				maxX = x > maxX ? x : maxX;
				
				minY = y < minY ? y : minY;
				maxY = y > maxY ? y : maxY;
			}
			
			//Calculate grid dimensions and create grid. Dimensions have +1 because 0 needs to be represented as well!
			int xDimension = Math.abs(minX) + maxX + 1;
			int yDimension = Math.abs(minY) + maxY + 1;
			int[][] gameGrid = new int[xDimension][yDimension];
			
			//Mark alive cells in grid.
			int originX = xDimension / 2;
			int originY = yDimension / 2;
			
			for(Cell aliveCell : this.aliveCells)
			{
				int cellX = originX + aliveCell.getX();
				int cellY = originY - aliveCell.getY();	// Uses - not addition because array indexes are not the same as Cartesian graphs! Y get's smaller as it goes vertically up.
				gameGrid[cellX][cellY] = 1;
			}
			
			//Print grid.
			result = Arrays.toString(gameGrid);
		}
		
		System.out.println(result);
	}
}
