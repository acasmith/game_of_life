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
	 * @param int[] aliveIndexes an integer array represented the cells in the original grid to initially set to alive.
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
	 * Origin cell is always represented as either '0' if it's alive, or '.' if it's dead.
	 */
	public String printGameBoard()
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
			int[][] gameGrid = new int[yDimension][xDimension]; //2d arrays are like a row of columns.
			
			//Find originCell index relative to array indexes.
			int originX = Math.abs(minX);
			int originY = Math.abs(maxY);
			
			//Mark alive cells in grid.
			for(Cell aliveCell : this.aliveCells)
			{
				int cellX = originX + aliveCell.getX();
				int cellY = originY - aliveCell.getY();	// Uses subtraction not addition because array indexes are not the same as Cartesian graphs! Y get's smaller as it goes vertically up.
				gameGrid[cellY][cellX] = 1;
			}
			
			gameGrid[originY][originX] = gameGrid[originY][originX] == 1 ? 9 : 8;
			
			//Format grid as string.
			result = this.formatGameGrid(gameGrid);
		}
		
		return result;
	}
	
	/**
	 * Changes the format of the game grid from 2d array to string.
	 * 
	 */
	private String formatGameGrid(int[][] gameGrid)
	{
		StringBuilder gridString = new StringBuilder("  ");
		
		//Add column indexes above grid
		for(int columnIndex = 0; columnIndex < gameGrid[0].length; columnIndex++)
		{
			gridString.append(columnIndex + " ");
		}
		gridString.append("\n");
		
		//Add each element to the string, a row at a time.
		for(int rowIndex = 0; rowIndex < gameGrid.length; rowIndex++)
		{
			StringBuilder rowString = new StringBuilder(rowIndex + "|");
			for(int columnIndex = 0; columnIndex < gameGrid[rowIndex].length; columnIndex++)
			{
				String toAppend = "";
				if(gameGrid[rowIndex][columnIndex] == 9)
				{
					toAppend = "0|";
				}
				else if(gameGrid[rowIndex][columnIndex] == 8)
				{
					toAppend = ".|";
				}
				else if(gameGrid[rowIndex][columnIndex] == 1)
				{
					toAppend = "A|";
				}
				else
				{
					toAppend = " |";
				}
				rowString.append(toAppend);
			}
			rowString.append("\n");
			gridString.append(rowString);
		}
		
		return gridString.toString();
	}
}
