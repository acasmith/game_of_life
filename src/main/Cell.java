package main;

import java.util.HashSet;
import java.util.Set;

/*
 * TODO:
 * Work on main algo.
 */

class Cell 
{
	int x;	//x coordinate on gameboard relative to the origin cell. -ve to the left of origin, +ve to the right of origin.
	int y;  //y coordinate on the gameboard relative to the origin cell. -ve below origin, +ve above origin.
	private boolean isAlive = false;
	private int aliveNeighbourCount = 0;
	/*
	 * Represents adjacent squares in a 3x3 matrix.
	 * index 0 = top left, 8 = bottom right  
	 * Self is index 4.
	 */
	public Cell[] neighbours = new Cell[9];	
	
	/**
	 * 0-arg Constructor. Set's the x and y coordinates to 0 (so it's the relative centre of the gameboard).
	 */
	public Cell()
	{
		this.x = 0;
		this.y = 0;
		this.neighbours[4] = this;
	}
	
	/**
	 * 2-arg constructor.
	 * @param x the x-coordinate of the cell relative to the origin cell.
	 * @param y the y-coordinate of the cell relative to the origin cell.
	 */
	public Cell(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.neighbours[4] = this;
	}

	/*
	 * Start Getters/Setters
	 */
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getAliveNeighbourCount() {
		return aliveNeighbourCount;
	}

	public void setAliveNeighbourCount(int aliveNeighbourCount) {
		this.aliveNeighbourCount = aliveNeighbourCount;
	}
	
	/*
	 * End Getters/Setters
	 */
	
	/** 
	 * Interconnects every cell in this cells neighbours array with every cell in the array that's 
	 * adjacent to it on the grid.
	 * Tracks every cell that has a neighbour reference changed, then calls this process on it again
	 * to ensure changes are propagated through grid.
	 */
	public Set<Cell> interconnectNeighbours()
	{
		Set<Cell> updatedCells = new HashSet<>();
		//For each cell in this.neighbours
		for(int currentElementIndex = 0; currentElementIndex < this.neighbours.length; currentElementIndex++)
		{
			if(this.neighbours[currentElementIndex] != null)
			{
				//Calculate indexes of neighbours that are connected to current Element
				int lowBoundary = Math.max(currentElementIndex - 4, 0);
				int highBoundary = Math.min(currentElementIndex + 4, this.neighbours.length - 1);
				
				//Calculate new index relative to current element, based on OG index.
				for(int i = lowBoundary; i <= highBoundary; i++)
				{
					//Catch range values that are not adjacent to cell.
					if(Math.max(currentElementIndex % 3, i % 3) == 2 && 
							Math.min(currentElementIndex % 3, i % 3) == 0)
					{
						continue;
					}
					
					/* Catch case where this cells recorded value is null.
					 * This prevents nulls replacing cell references when
					 * changes are propagated through grid by calling interconnect on cells 
					 * whose neighbours array has just been updated.
					 */
					if(!(this.neighbours[i] != null))
					{
						continue;
					}
					
					//Calculate shift and assign to currentElement's neighbours.
					int difference = currentElementIndex - i;
					if(this.neighbours[currentElementIndex].neighbours[4 - difference] != this.neighbours[i])
					{
						this.neighbours[currentElementIndex].neighbours[4 - difference] = this.neighbours[i];
						updatedCells.add(this.neighbours[currentElementIndex].neighbours[4 - difference]);
					}
				}
			}
		}
		
		//Recursive call to propagate changes through grid.
		for(Cell aCell : updatedCells)
		{
			aCell.interconnectNeighbours();
		}
		return updatedCells;
	}
	
	/**
	 * Handles the process of switching the cell from dead to alive state.
	 */
	public void doBirth()
	{
		this.setAlive(true);
		this.extendGrid();
		//Interconnect new cells with existing neighbours, then propagate new cell references through the grid.
		this.interconnectNeighbours();
		this.informNeighboursAliveStatus();
		
	}
	
	/**
	 * Tells each non-null neighbour about this cells change in isAlive state.
	 */
	private void informNeighboursAliveStatus()
	{
		for(int i = 0; i < this.neighbours.length; i++)
		{
			if(this.neighbours[i] != null)
			{
				if(this.isAlive)
				{
					this.neighbours[i].neighbourBorn();
				}
				else
				{
					this.neighbours[i].neighbourDied();
				}
			}
		}
	}
	
	/**
	 * Increments aliveNeighbourCount
	 */
	public void neighbourBorn()
	{
		this.aliveNeighbourCount++;
	}
	
	/**
	 * Decrements aliveNeighbourCount.
	 */
	public void neighbourDied() 
	{
		this.aliveNeighbourCount--;
	}
	
	/**
	 * Extends the game grid by filling out missing neighbours around this cell with dead cells.
	 */
	private void extendGrid()
	{
		for(int i = 0; i < this.neighbours.length; i++)
		{
			if(!(this.neighbours[i] != null))
			{
				//Got to be a math way to do this without using a hardcoded array?
				int[] diffArray = {-1, 0, 1}; //Array index contains the amount the new cell coordinate needs to shift by, relative to origin cell.
				int xCoord = this.getX() + diffArray[i % 3];
				int yCoord = this.getY() + (diffArray[i / 3] * -1);
				this.neighbours[i] = i == 4 ? this : new Cell(xCoord, yCoord);
			}
		}
	}
	
	/**
	 * Handles the initial setup of the grid when the cell is the original, first cell in the grid.
	 */
	public void makeOriginCell()
	{
		this.doBirth();
		this.setAlive(false);
	}
}
