package main;

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
			//Calculate indexes of neighbours that are connected to current Element
			int lowBoundary = Math.max(currentElementIndex - 4, 0);
			int highBoundary = Math.min(currentElementIndex + 4, neighbours.length - 1);
			
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
				neighbours[currentElementIndex].neighbours[4 - difference] = neighbours[i];
			}
		}
	}
	
}
