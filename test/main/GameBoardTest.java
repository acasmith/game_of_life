package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameBoardTest {
	
	int[] indexArray = new int[0];

	@Test
	void initialGridCellCount() 
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		assertTrue(result.neighbours.length == 9, "The original cell should have 8 neighbours");
		
		
	}
	
	@Test
	void initialGridNotNull()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		boolean isNull = true;
		for(int i = 0; i < result.neighbours.length; i++)
		{
			isNull = !(result.neighbours[i] != null);
		}
		assertFalse(isNull, "A Cells initial neighbours collection should all be non-null.");
	}
	
	@Test
	void initialGridSelfCheck()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		assertTrue(result == result.neighbours[4], "Neighours collection should reference self at index 4");
	}
	
	
	//Explicitly testing permutations because any condensed tests would use the same logic as the method under test.
	
	@Test
	void initialGridInterconnection0()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		Cell neighbour0 = result.neighbours[0];
		int cellCount = 0;
		for(int i = 0; i < neighbour0.neighbours.length; i++)
		{
			if(neighbour0.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 4, "result.neighbours[0] should be connected to 4 cells. Was connected to " + cellCount);
		assertTrue(neighbour0.neighbours[4] == neighbour0, "result.neighbours[0] should reference itself at it's neighbours[4].");
		assertTrue(neighbour0.neighbours[5] == result.neighbours[1], "result.neighbours[0] should reference result.neighbours[1] at index 5.");
		assertTrue(neighbour0.neighbours[7] == result.neighbours[3], "result.neighbours[0] should reference result.neighbours[3] at index 7.");
		assertTrue(neighbour0.neighbours[8] == result, "result.neighbours[0] should reference result at index 8.");
		
		assertFalse(neighbour0.neighbours[6] != null, "neighbour0.neighbours[6] should be null");
	}
	
	
	@Test
	void initialGridInterconnection1()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		Cell neighbour1 = result.neighbours[1];
		int cellCount = 0;
		for(int i = 0; i < neighbour1.neighbours.length; i++)
		{
			if(neighbour1.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 6, "result.neighbours[1] should be connected to 6 cells. Was connected to " + cellCount);
		assertTrue(neighbour1.neighbours[4] == neighbour1, "result.neighbours[1] should reference itself at it's neighbours[4].");
		assertTrue(neighbour1.neighbours[3] == result.neighbours[0], "result.neighbours[1] should reference result.neighbours[0] at index 3.");
		assertTrue(neighbour1.neighbours[5] == result.neighbours[2], "result.neighbours[1] should reference result.neighbours[2] at index 5.");
		assertTrue(neighbour1.neighbours[6] == result.neighbours[3], "result.neighbours[1] should reference result.neighbours[3] at index 6.");
		assertTrue(neighbour1.neighbours[7] == result, "result.neighbours[1] should reference result at index 7.");
		assertTrue(neighbour1.neighbours[8] == result.neighbours[5], "result.neighbours[1] should reference result.neighbours[5] at index 8.");
		
		assertFalse(neighbour1.neighbours[2] != null, "neighbour0.neighbours[2] should be null");
	}
	
	@Test
	void initialGridInterconnection2()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		Cell neighbour2 = result.neighbours[2];
		int cellCount = 0;
		for(int i = 0; i < neighbour2.neighbours.length; i++)
		{
			if(neighbour2.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 4, "result.neighbours[2] should be connected to 4 cells. Was connected to " + cellCount);
		assertTrue(neighbour2.neighbours[4] == neighbour2, "result.neighbours[2] should reference itself at it's neighbours[4].");
		assertTrue(neighbour2.neighbours[3] == result.neighbours[1], "result.neighbours[2] should reference result.neighbours[1] at index 3.");
		assertTrue(neighbour2.neighbours[6] == result.neighbours[4], "result.neighbours[2] should reference result.neighbours[4] at index 6.");
		assertTrue(neighbour2.neighbours[7] == result.neighbours[5], "result.neighbours[2] should reference result.neighbours[5] at index 7.");
		
		assertFalse(neighbour2.neighbours[2] != null, "neighbour0.neighbours[2] should be null");
	}
	
	@Test
	void initialGridInterconnection3()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		Cell neighbour = result.neighbours[3];
		int cellCount = 0;
		for(int i = 0; i < neighbour.neighbours.length; i++)
		{
			if(neighbour.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 6, "result.neighbours[3] should be connected to 6 cells. Was connected to " + cellCount);
		assertTrue(neighbour.neighbours[4] == neighbour, "result.neighbours[3] should reference itself at it's neighbours[4].");
		assertTrue(neighbour.neighbours[1] == result.neighbours[0], "result.neighbours[3] should reference result.neighbours[0] at index 1.");
		assertTrue(neighbour.neighbours[2] == result.neighbours[1], "result.neighbours[3] should reference result.neighbours[1] at index 2.");
		assertTrue(neighbour.neighbours[5] == result, "result.neighbours[3] should reference result at index 5.");
		assertTrue(neighbour.neighbours[7] == result.neighbours[6], "result.neighbours[3] should reference result.neighbours[6] at index 7.");
		assertTrue(neighbour.neighbours[8] == result.neighbours[7], "result.neighbours[3] should reference result.neighbours[7] at index 8.");
		
		assertFalse(neighbour.neighbours[0] != null, "neighbour3.neighbours[0] should be null");
	}
	
	//Neighbour 4 is the original cell, and it's interconnections are already tested.
	
	@Test
	void initialGridInterconnection5()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		Cell neighbour = result.neighbours[5];
		int cellCount = 0;
		for(int i = 0; i < neighbour.neighbours.length; i++)
		{
			if(neighbour.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 6, "result.neighbours[5] should be connected to 6 cells. Was connected to " + cellCount);
		assertTrue(neighbour.neighbours[4] == neighbour, "result.neighbours[5] should reference itself at it's neighbours[4].");
		assertTrue(neighbour.neighbours[0] == result.neighbours[1], "result.neighbours[5] should reference result.neighbours[0] at index 0.");
		assertTrue(neighbour.neighbours[1] == result.neighbours[2], "result.neighbours[5] should reference result.neighbours[2] at index 1.");
		assertTrue(neighbour.neighbours[3] == result, "result.neighbours[5] should reference result at index 3.");
		assertTrue(neighbour.neighbours[6] == result.neighbours[7], "result.neighbours[5] should reference result.neighbours[7] at index 6.");
		assertTrue(neighbour.neighbours[7] == result.neighbours[8], "result.neighbours[5] should reference result.neighbours[8] at index 7.");
		
		assertFalse(neighbour.neighbours[2] != null, "neighbour3.neighbours[0] should be null");
	}
	
	@Test
	void initialGridInterconnection6()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		Cell neighbour = result.neighbours[6];
		int cellCount = 0;
		for(int i = 0; i < neighbour.neighbours.length; i++)
		{
			if(neighbour.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 4, "result.neighbours[6] should be connected to 4 cells. Was connected to " + cellCount);
		assertTrue(neighbour.neighbours[4] == neighbour, "result.neighbours[6] should reference itself at it's neighbours[4].");
		assertTrue(neighbour.neighbours[2] == result, "result.neighbours[6] should reference result at index 1.");
		assertTrue(neighbour.neighbours[1] == result.neighbours[3], "result.neighbours[6] should reference result.neighbours[5] at index 1.");
		assertTrue(neighbour.neighbours[5] == result.neighbours[7], "result.neighbours[6] should reference result.neighbours[7] at index 5.");
		
		assertFalse(neighbour.neighbours[0] != null, "neighbour0.neighbours[2] should be null");
	}
	
	@Test
	void initialGridInterconnection7()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		Cell neighbour = result.neighbours[7];
		int cellCount = 0;
		for(int i = 0; i < neighbour.neighbours.length; i++)
		{
			if(neighbour.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 6, "result.neighbours[7] should be connected to 6 cells. Was connected to " + cellCount);
		assertTrue(neighbour.neighbours[4] == neighbour, "result.neighbours[5] should reference itself at it's neighbours[4].");
		assertTrue(neighbour.neighbours[0] == result.neighbours[3], "result.neighbours[7] should reference result.neighbours[3] at index 0.");
		assertTrue(neighbour.neighbours[2] == result.neighbours[5], "result.neighbours[7] should reference result.neighbours[5] at index 2.");
		assertTrue(neighbour.neighbours[1] == result, "result.neighbours[5] should reference result at index 1.");
		assertTrue(neighbour.neighbours[3] == result.neighbours[6], "result.neighbours[7] should reference result.neighbours[6] at index 3.");
		assertTrue(neighbour.neighbours[5] == result.neighbours[8], "result.neighbours[7] should reference result.neighbours[7] at index 8.");
		
		assertFalse(neighbour.neighbours[6] != null, "neighbour3.neighbours[0] should be null");
	}
	
	@Test
	void initialGridInterconnection8()
	{
		Cell result = GameBoard.createInitialGrid(indexArray);
		Cell neighbour8 = result.neighbours[8];
		int cellCount = 0;
		for(int i = 0; i < neighbour8.neighbours.length; i++)
		{
			if(neighbour8.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 4, "result.neighbours[8] should be connected to 4 cells. Was connected to " + cellCount);
		assertTrue(neighbour8.neighbours[4] == neighbour8, "result.neighbours[8] should reference itself at it's neighbours[4].");
		assertTrue(neighbour8.neighbours[3] == result.neighbours[7], "result.neighbours[8] should reference result.neighbours[7] at index 3.");
		assertTrue(neighbour8.neighbours[1] == result.neighbours[5], "result.neighbours[8] should reference result.neighbours[5] at index 1.");
		assertTrue(neighbour8.neighbours[0] == result, "result.neighbours[8] should reference result at index 0.");
		
		assertFalse(neighbour8.neighbours[2] != null, "neighbour0.neighbours[2] should be null");
	}
	

}
