package main;

/*
 * TODO:
 * Add grid extension when cell turned alive to Cell class.
 */

class Cell 
{
	private boolean isAlive = false;
	private int aliveNeighbourCount = 0;
	/*
	 * Represents adjacent squares in a 3x3 matrix.
	 * index 0 = top left, 8 = bottom right  
	 * Self is index 4.
	 */
	public Cell[] neighbours = new Cell[9];	
	
	/**
	 * Constructor
	 */
	public Cell()
	{
		
	}

	/*
	 * Start Getters/Setters
	 */
	
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
	 * 
	 */
	public void interconnectNeighbours()
	{
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
					
					//Calculate shift and assign to currentElement's neighbours.
					int difference = currentElementIndex - i;
					this.neighbours[currentElementIndex].neighbours[4 - difference] = this.neighbours[i];
				}
			}
		}
	}
	
	/**
	 * Handles the process of switching the cell from dead to alive state.
	 */
	public void doBirth()
	{
		this.setAlive(true);
		this.informNeighboursAliveStatus();
		this.extendGrid();
		this.interconnectNeighbours();
		
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
				this.neighbours[i] = new Cell();
			}
		}
	}
}
