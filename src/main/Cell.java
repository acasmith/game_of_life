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
	
	
}
