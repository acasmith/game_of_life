package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Adam Smith
 * A class to represent the infinite board on which the game is played.
 * The object is effectively immutable. If you want a new game, make a new object.
 * This is because there's a synergy between starting a new game of life and a new GoL object.
 *
 */

class GameOfLife
{	
	GameBoard aBoard;
	int maxIterations;
	
	/**
	 * Constructor
	 */
	public GameOfLife(int maxIterations)
	{
		this.maxIterations = maxIterations;
	}
	
	/**
	 * Initiates a new Game of Life.
	 * @param aliveIndexes an array containing the integer indexes of the initial alive cells in the 3x3 starting grid.
	 */
	public String startGame(int[] aliveIndexes)
	{
		//Prevent duplicate indexes and filter for indexes outside of the integer range 0-8.
		Set<Integer> aliveIndexesSet = new HashSet<Integer>();
		for(int index : aliveIndexes)
		{
			if(index >= 0 && index <= 8)
			{
				aliveIndexesSet.add(index);
			}
			
		}
		
		
		this.aBoard = new GameBoard(aliveIndexesSet);
		System.out.println("Initial board: \n" + this.aBoard.printGameBoard());
		String finalResult = this.doIteration(0);
		return finalResult;
		
	}

	/**
	 * Causes the next iteration in the game to occur by adding new live cells and killing others.
	 * Checks for the end states of no alive cells and maximum iterations.
	 */
	private String doIteration(int currentIteration)
	{
		String turnResult = "";
		
		//Make checks.
		if(this.aBoard.aliveCells.isEmpty())
		{
			turnResult = "Game ended: all cells are dead.";
		}
		else if(currentIteration >= this.maxIterations)
		{
			turnResult = "Game ended: Max iterations (" + this.maxIterations + ") reached.";
		}
		
		//If checks failed, print reason and finish game.
		if(turnResult != "")
		{
			return turnResult;
		}
		
		//Turn actions
		//1. Mark for deletion
		List<Cell> markedCells = new ArrayList<>();
		for(Cell aliveCell : this.aBoard.aliveCells)
		{
			if(aliveCell.getAliveNeighbourCount() < 2 ||
					aliveCell.getAliveNeighbourCount() > 3)
			{
				markedCells.add(aliveCell);
			}
		}
		
		//2. Check all dead neighbours of alive cells for creation.
		Set<Cell> newBorns = new HashSet<>();
		for(Cell aliveCell : this.aBoard.aliveCells)
		{
			for(Cell neighbourCell : aliveCell.neighbours)
			{
				if(!neighbourCell.isAlive() && neighbourCell.getAliveNeighbourCount() == 3)
				{
					newBorns.add(neighbourCell);
				}
			}
		}
		
		//Add new cells to grid.
		for(Cell newBorn : newBorns)
		{
			newBorn.doBirth();
			this.aBoard.aliveCells.add(newBorn);
		}
		
		//3. Destroy marked cells
		for(Cell markedCell : markedCells)
		{
			markedCell.kill();
			this.aBoard.aliveCells.remove(markedCell);
		}
		
		//4. Create board visualisation and print results
		System.out.println("Turn " + (currentIteration + 1)+ ": \n" + this.printGameBoard() + "\n");
		
		//5. Recursive call
		return this.doIteration(currentIteration + 1);
	}
	
	public String printGameBoard()
	{
		return this.aBoard.printGameBoard();
	}
	
	
}
