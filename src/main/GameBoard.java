package main;

public class GameBoard 
{
	/**
	 * Initialises the game board to a 3x3 grid of dead cells..
	 * @return List<Cell> a list 
	 */
	public static Cell createInitialGrid()
	{
		//Create Cell
		//Fill neighbours with dead cells.
		//Send neighbour lists to neighbours.
		//Set aliveCells and store them in this.aliveCells
		
		
		Cell originalCell = new Cell();
		Cell[] initialGrid = new Cell[8];
		for(int i = 0; i < initialGrid.length; i++)
		{
			initialGrid[i] = new Cell();
		}
		
		
		
		return originalCell;
	}
}
