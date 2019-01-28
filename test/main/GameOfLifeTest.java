package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameOfLifeTest {

	@Test
	void initialGridCellCount() 
	{
		Cell result = GameBoard.createInitialGrid();
		assertTrue(result.neighbours.length == 8, "The original cell should have 8 neighbours");
		
		Cell aCell = GameBoard.createInitialGrid();
		boolean isNull = true;
		for(int i = 0; i < aCell.neighbours.length; i++)
		{
			isNull = (aCell.neighbours[i] != null);
		}
		assertFalse(isNull, "A Cells initial neighbours array should all be non-null.");
	}
	
	
	
	

}
