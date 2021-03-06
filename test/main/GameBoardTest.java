package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {
	
	Integer[] indexArray;
	Set<Integer> indexSet;
	Cell originCell;
	GameBoard aBoard;
	
	@BeforeEach public void initalize()
	{
		this.indexArray = new Integer[1];
		this.indexArray[0] = 4;
		this.indexSet = new HashSet<>(Arrays.asList(this.indexArray));
		
		this.aBoard = new GameBoard(this.indexSet);
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
		Integer[] indexArray = new Integer[0];
		this.indexSet = new HashSet<>(Arrays.asList(indexArray));
		this.aBoard = new GameBoard(indexSet);
		assertTrue(this.aBoard.printGameBoard() == "All cells are dead.");
	}
	
	@Test
	void printGameBoardFull()
	{
		String targetString = "  0 1 2 \n" + 
				"0|A|A|A|\n" + 
				"1|A|0|A|\n" + 
				"2|A|A|A|\n";
		
		Integer[] indexArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		this.indexSet = new HashSet<>(Arrays.asList(indexArray));
		this.aBoard = new GameBoard(this.indexSet);
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
				"2| | |.|\n";
		
		Integer[] indexArray = {0};
		this.indexSet = new HashSet<>(Arrays.asList(indexArray));
		this.aBoard = new GameBoard(this.indexSet);
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
				"2| | |.| | |\n" + 
				"3| | | |A| |\n" + 
				"4| | | | |A|\n";
		
		Integer[] indexArray = {0, 8};
		this.indexSet = new HashSet<>(Arrays.asList(indexArray));
		this.aBoard = new GameBoard(this.indexSet);
		
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
				"2| | |.| | |\n" + 
				"3| |A| | | |\n" + 
				"4|A| | | | |\n";
		
		Integer[] indexArray = {2, 6};
		this.indexSet = new HashSet<>(Arrays.asList(indexArray));
		this.aBoard = new GameBoard(this.indexSet);
		
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
				"2|A|A|.|\n";
		
		Integer[] indexArray = {1, 3};
		this.indexSet = new HashSet<>(Arrays.asList(indexArray));
		this.aBoard = new GameBoard(this.indexSet);

		
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
				"0|.|A|A|\n" + 
				"1|A| | |\n" + 
				"2|A| | |\n";
		
		Integer[] indexArray = {5, 7};
		this.indexSet = new HashSet<>(Arrays.asList(indexArray));
		this.aBoard = new GameBoard(this.indexSet);
		
		this.aBoard.aliveCells.get(0).neighbours[5].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(0).neighbours[5]);
		
		this.aBoard.aliveCells.get(1).neighbours[7].doBirth();
		this.aBoard.aliveCells.add(this.aBoard.aliveCells.get(1).neighbours[7]);
		
		String resultString = this.aBoard.printGameBoard();
		
		assertTrue(resultString.length() == targetString.length(), "Length should be " + resultString.length() + ", was " + targetString.length());
		assertTrue(resultString.equals(targetString), "Result should produce the target string.");
	}
	
	@Test
	void printGameBoard2Cells()
	{
		Integer[] indexArray = {0, 3, 4, 6, 8};
		this.indexSet = new HashSet<>(Arrays.asList(indexArray));
		this.aBoard = new GameBoard(this.indexSet);
		String targetString = "  0 1 2 \n" + 
				"0|A| | |\n" + 
				"1|A|0| |\n" + 
				"2|A| |A|\n";
		String resultString = this.aBoard.printGameBoard();
		System.out.println(resultString);
		assertTrue(resultString.equals(targetString));
	}

}
