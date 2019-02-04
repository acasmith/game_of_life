package main;

import java.util.HashSet;
import java.util.Set;

/*
 * TODO:
 * Test main algo.
 * Change print algorithm so that 0,0 printed over origin, and indexes are based from that.
 * Add checks for initial live cells array to avoid out of range exception if < 0 or > 8.
 * Put initial alive cells arary into a set, so you avoid issues with multiples of same index.
 * 
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
	
	public Cell getNeighbour(int neighbourIndex)
	{
		return this.neighbours[neighbourIndex];
	}
	
	public void setNeighbour(int neighbourIndex, Cell aCell)
	{
		this.neighbours[neighbourIndex] = aCell;
	}
	
	public int getNeighboursLength()
	{
		return this.neighbours.length;
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
		for(int currentElementIndex = 0; currentElementIndex < this.getNeighboursLength(); currentElementIndex++)
		{
			if(this.getNeighbour(currentElementIndex) != null)
			{
				//Calculate indexes of neighbours that are connected to current Element
				int lowBoundary = Math.max(currentElementIndex - 4, 0);
				int highBoundary = Math.min(currentElementIndex + 4, this.getNeighboursLength() - 1);
				
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
					if(!(this.getNeighbour(i) != null))
					{
						continue;
					}
					
					//Calculate shift and assign to currentElement's neighbours.
					int difference = currentElementIndex - i;
					if(this.getNeighbour(currentElementIndex).getNeighbour(4 - difference) != this.getNeighbour(i))
					{
						this.getNeighbour(currentElementIndex).setNeighbour(4 - difference, this.getNeighbour(i));
						updatedCells.add(this.getNeighbour(currentElementIndex).getNeighbour(4 - difference));
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
		for(int i = 0; i < this.getNeighboursLength(); i++)
		{
			if(this.getNeighbour(i) != null && i != 4)
			{
				if(this.isAlive)
				{
					this.getNeighbour(i).neighbourBorn();
				}
				else
				{
					this.getNeighbour(i).neighbourDied();
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
		
		if(!this.isAlive() && this.getAliveNeighbourCount()  <= 0)
		{
			this.cellPermaDeath();
		}
	}
	
	/**
	 * Extends the game grid by filling out missing neighbours around this cell with dead cells.
	 */
	private void extendGrid()
	{
		for(int i = 0; i < this.getNeighboursLength(); i++)
		{
			if(!(this.getNeighbour(i) != null))
			{
				//Got to be a math way to do this without using a hardcoded array?
				int[] diffArray = {-1, 0, 1}; //Array index contains the amount the new cell coordinate needs to shift by, relative to origin cell.
				int xCoord = this.getX() + diffArray[i % 3];
				int yCoord = this.getY() + (diffArray[i / 3] * -1);
				Cell newCell = i == 4 ? this : new Cell(xCoord, yCoord);
				this.setNeighbour(i, newCell);
			}
		}
	}
	
	/**
	 * Handles the initial setup of the grid when the cell is the original, first cell in the grid.
	 */
	public void makeOriginCell()
	{
		this.extendGrid();
		//Interconnect new cells with existing neighbours, then propagate new cell references through the grid.
		this.interconnectNeighbours();;
	}
	
	/**
	 * Method to kill the cell, and propagate this change across the game grid.
	 */
	public void kill()
	{
		//Change alive status
		this.setAlive(false);
		
		/*
		 * Inform neighbours of death and if necessary remove any references to 
		 * self form neighbours to keep grid clean of dead cells with no alive neighbours.
		 * No alive neighbours means they can't be born, so it's not necessary to track them with an object.
		 */
		for(int i = 0; i < this.getNeighboursLength(); i++)
		{
			if(i != 4)
			{
				this.getNeighbour(i).neighbourDied();
			}
		}
		
		if(this.getAliveNeighbourCount() <= 0)
		{
			this.cellPermaDeath();
		}
	}
	
	/* Useful for debugging
	public int countAliveNeighbours()
	{
		int result = 0;
		for(Cell aCell : this.neighbours)
		{
			if(aCell != this && aCell.isAlive())
			{
				result++;
			}
		}
		return result;
	}
	*/
	
	/*
	 * Removes any references to the cell, and removes any references the cell has to other cells.
	 */
	private void cellPermaDeath()
	{
		for(int i = 0; i < this.getNeighboursLength(); i++)
		{
			if(this.getNeighbour(i) != null)
			{
				int shift = 4 + (4 - i);
				this.getNeighbour(i).setNeighbour(shift, null);
			}
		}
		
		this.neighbours = null;
	}
}
