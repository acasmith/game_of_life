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
	

}
