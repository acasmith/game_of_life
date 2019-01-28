package main;


/**
 * 
 * @author Adam
 * A class to represent the infinite board on which the game is played. 
 */

/*
 * TODO: filter initial board for alive cells. Store them in a list. Unsure whether to store it in this class
 * and use it to represent board, and have game logic create an instance of it. That seems better than game tracking it itself.
 * 		change createInitialGrid to createOriginalCell
 * 		change the tests to reflect name change
 * 		create method called createInitialGrid that sets an instance var to a list of alive cells.
 * 		Change everything from static to normal, and start the cascade from the constructor.
 * 		
 * 		
 */
public class GameBoard 
{
	/**
	 * Initialises the game board to a 3x3 grid of dead cells.
	 * @return List<Cell> a list of the alive cells on the grid.
	 */
	public static Cell createInitialGrid(int[] aliveIndexes)
	{
		//Create Cell
		Cell originalCell = new Cell();
		
		//Fill neighbours with dead cells.
		Cell[] initialGrid = new Cell[9];
		for(int i = 0; i < initialGrid.length; i++)
		{
			
			initialGrid[i] = i == 4 ? originalCell : new Cell();
		}
		originalCell.neighbours = initialGrid;
		
		//Send neighbour lists to neighbours.
		GameBoard.connectInitialNeighbours(originalCell);
		
		//Set aliveCells and store them in this.aliveCells
		GameBoard.setInitialAliveCells(originalCell, aliveIndexes);
		
		return originalCell;
	}
	
	/*
	 * Sets up the connections between each cell and it's neighbours in the initial grid.
	 */
	private static void connectInitialNeighbours(Cell originalCell)
	{
		for(int i = 0; i < originalCell.neighbours.length; i++)
		{
			createInitialNeighbourArray(originalCell.neighbours, i);
		}
	}
	
	/*
	 * Creates an initial cell's neighbours array based on it's position in the original cell's neighbours array.
	 */
	private static void createInitialNeighbourArray(Cell[] neighbours, int currentElementIndex)
	{
		//Calculate indexes of OG neighbours that are connected to current Element
		//TODO: further tests
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
	
	/*
	 * Activates specific cells in the initial grid to become alive, according to a set of indexes.
	 */
	private static void setInitialAliveCells(Cell originalCell, int[] aliveIndexes)
	{
		for(int i = 0; i < aliveIndexes.length; i ++)
		{
			originalCell.neighbours[aliveIndexes[i]].setAlive(true);
		}
	}
}
