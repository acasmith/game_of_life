package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {
	
	int[] indexArray;
	Cell originCell;
	GameBoard aBoard;
	
	@BeforeEach public void initalize()
	{
		this.indexArray = new int[1];
		this.indexArray[0] = 4;
		this.aBoard = new GameBoard(this.indexArray);
		this.originCell = this.aBoard.aliveCells.get(0);
	}

	@Test
	void initialGridCellCount() 
	{
		assertTrue(this.originCell.neighbours.length == 9, "The original cell should have 8 neighbours");
		
		
	}
	
	@Test
	void initialGridNotNull()
	{
		boolean isNull = true;
		for(int i = 0; i < this.originCell.neighbours.length; i++)
		{
			isNull = !(this.originCell.neighbours[i] != null);
		}
		assertFalse(isNull, "The original cell's initial neighbours collection should all be non-null.");
	}
	
	@Test
	void initialGridSelfCheck()
	{
		assertTrue(this.originCell == this.originCell.neighbours[4], "Neighbours collection should reference self at index 4");
	}
	
	@Test
	void printGameBoardEmpty()
	{
		int[] indexArray = new int[0];
		this.aBoard = new GameBoard(indexArray);
		assertTrue(this.aBoard.printGameBoard() == "All cells are dead.");
	}
	
	@Test
	void printGameBoardFull()
	{
		String targetString = "  0 1 2 \n" + 
				"0|A|A|A|\n" + 
				"1|A|A|A|\n" + 
				"2|A|A|A|\n";
		
		int[] indexArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		this.aBoard = new GameBoard(indexArray);
		String resultString = this.aBoard.printGameBoard();
		
		assertTrue(resultString.length() == targetString.length(), "Length should be " + resultString.length() + ", was " + targetString.length());
		assertTrue(resultString.equals(targetString), "Result should produce the target string.");
	}
	
	@Test
	void printGameBoardNonInitialCellTopLeft()
	{
		String targetString = "  0 1 2 \n" + 
				"0|A| | |\n" + 
				"1| |A| |\n" + 
				"2| | | |\n";
		
		int[] indexArray = {0};
		this.aBoard = new GameBoard(indexArray);
		this.aBoard.aliveCells.get(0).neighbours[0].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(0).neighbours[0]);
		
		String resultString = this.aBoard.printGameBoard();
		
		assertTrue(resultString.length() == targetString.length(), "Length should be " + resultString.length() + ", was " + targetString.length());
		assertTrue(resultString.equals(targetString), "Result should produce the target string.");
	}
	
	@Test
	void printGameBoard2NonInitialCells()
	{
		String targetString = "  0 1 2 3 4 \n" + 
				"0|A| | | | |\n" + 
				"1| |A| | | |\n" + 
				"2| | | | | |\n" + 
				"3| | | |A| |\n" + 
				"4| | | | |A|\n";
		
		int[] indexArray = {0, 8};
		this.aBoard = new GameBoard(indexArray);
		
		this.aBoard.aliveCells.get(0).neighbours[0].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(0).neighbours[0]);
		
		this.aBoard.aliveCells.get(1).neighbours[8].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(1).neighbours[8]);
		
		String resultString = this.aBoard.printGameBoard();
		
		assertTrue(resultString.length() == targetString.length(), "Length should be " + resultString.length() + ", was " + targetString.length());
		assertTrue(resultString.equals(targetString), "Result should produce the target string.");
	}
	
	@Test
	void printGameBoard2MoreNonInitialCells()
	{
		String targetString = "  0 1 2 3 4 \n" + 
				"0| | | | |A|\n" + 
				"1| | | |A| |\n" + 
				"2| | | | | |\n" + 
				"3| |A| | | |\n" + 
				"4|A| | | | |\n";
		
		int[] indexArray = {2, 6};
		this.aBoard = new GameBoard(indexArray);
		
		this.aBoard.aliveCells.get(0).neighbours[2].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(0).neighbours[2]);
		
		this.aBoard.aliveCells.get(1).neighbours[6].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(1).neighbours[6]);
		
		String resultString = this.aBoard.printGameBoard();
		
		assertTrue(resultString.length() == targetString.length(), "Length should be " + resultString.length() + ", was " + targetString.length());
		assertTrue(resultString.equals(targetString), "Result should produce the target string.");
	}
	
	@Test
	void printGameBoard2CellsAdjacentSides()
	{
		String targetString = "  0 1 2 \n" + 
				"0| | |A|\n" + 
				"1| | |A|\n" + 
				"2|A|A| |\n";
		
		int[] indexArray = {1, 3};
		this.aBoard = new GameBoard(indexArray);
		
		this.aBoard.aliveCells.get(0).neighbours[1].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(0).neighbours[1]);
		
		this.aBoard.aliveCells.get(1).neighbours[3].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(1).neighbours[3]);
		
		String resultString = this.aBoard.printGameBoard();
		
		assertTrue(resultString.length() == targetString.length(), "Length should be " + resultString.length() + ", was " + targetString.length());
		assertTrue(resultString.equals(targetString), "Result should produce the target string.");
	}
	
	@Test
	void printGameBoard2CellsOtherAdjacentSides()
	{
		String targetString = "  0 1 2 \n" + 
				"0| |A|A|\n" + 
				"1|A| | |\n" + 
				"2|A| | |\n";
		
		int[] indexArray = {5, 7};
		this.aBoard = new GameBoard(indexArray);
		
		this.aBoard.aliveCells.get(0).neighbours[5].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(0).neighbours[5]);
		
		this.aBoard.aliveCells.get(1).neighbours[7].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(1).neighbours[7]);
		
		String resultString = this.aBoard.printGameBoard();
		
		assertTrue(resultString.length() == targetString.length(), "Length should be " + resultString.length() + ", was " + targetString.length());
		assertTrue(resultString.equals(targetString), "Result should produce the target string.");
	}

}
