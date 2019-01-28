package main;

public class Cell 
{
	private boolean isAlive = false;
	private int aliveNeighbourCount = 0;
	private Cell[] neighbours = new Cell[8];	//Represents adjacent squares where index 0 = top left going clockwise.
	
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
