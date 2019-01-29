package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {
	
	int[] indexArray = new int[0];
	Cell aCell;
	
	@BeforeEach public void initialize()
	{
		this.aCell = new Cell();
		
		//Fill neighbours with dead cells.
		Cell[] initialGrid = new Cell[9];
		for(int i = 0; i < initialGrid.length; i++)
		{
			initialGrid[i] = i == 4 ? aCell : new Cell();
		}
		aCell.neighbours = initialGrid;
		this.aCell.interconnectNeighbours();
	}
	
	@Test
	void testInitialNullArray()
	{
		this.aCell = new Cell(); //Want to avoid setting neighbours via initialize.
		boolean isNull = true;
		for(int i = 0; i < aCell.neighbours.length; i++)
		{
			isNull = !(aCell.neighbours[i] != null);
		}
		assertTrue(isNull, "A Cells initial neighbours array should all be null.");
	}
	
	@Test
	void interconnectNeighbourSelfCheck()
	{
		assertTrue(aCell == aCell.neighbours[4], "Neighours collection should reference self at index 4");
	}
	
	
	//Explicitly testing permutations because any condensed tests would use the same logic as the method under test.
	
	@Test
	void interconnectNeighbour0()
	{
		Cell neighbour0 = aCell.neighbours[0];
		int cellCount = 0;
		for(int i = 0; i < neighbour0.neighbours.length; i++)
		{
			if(neighbour0.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 4, "aCell.neighbours[0] should be connected to 4 cells. Was connected to " + cellCount);
		assertTrue(neighbour0.neighbours[4] == neighbour0, "aCell.neighbours[0] should reference itself at it's neighbours[4].");
		assertTrue(neighbour0.neighbours[5] == aCell.neighbours[1], "aCell.neighbours[0] should reference aCell.neighbours[1] at index 5.");
		assertTrue(neighbour0.neighbours[7] == aCell.neighbours[3], "aCell.neighbours[0] should reference aCell.neighbours[3] at index 7.");
		assertTrue(neighbour0.neighbours[8] == aCell, "aCell.neighbours[0] should reference aCell at index 8.");
		
		assertFalse(neighbour0.neighbours[6] != null, "neighbour0.neighbours[6] should be null");
	}
	
	
	@Test
	void initialGridInterconnection1()
	{
		Cell neighbour1 = aCell.neighbours[1];
		int cellCount = 0;
		for(int i = 0; i < neighbour1.neighbours.length; i++)
		{
			if(neighbour1.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 6, "aCell.neighbours[1] should be connected to 6 cells. Was connected to " + cellCount);
		assertTrue(neighbour1.neighbours[4] == neighbour1, "aCell.neighbours[1] should reference itself at it's neighbours[4].");
		assertTrue(neighbour1.neighbours[3] == aCell.neighbours[0], "aCell.neighbours[1] should reference aCell.neighbours[0] at index 3.");
		assertTrue(neighbour1.neighbours[5] == aCell.neighbours[2], "aCell.neighbours[1] should reference aCell.neighbours[2] at index 5.");
		assertTrue(neighbour1.neighbours[6] == aCell.neighbours[3], "aCell.neighbours[1] should reference aCell.neighbours[3] at index 6.");
		assertTrue(neighbour1.neighbours[7] == aCell, "aCell.neighbours[1] should reference aCell at index 7.");
		assertTrue(neighbour1.neighbours[8] == aCell.neighbours[5], "aCell.neighbours[1] should reference aCell.neighbours[5] at index 8.");
		
		assertFalse(neighbour1.neighbours[2] != null, "neighbour0.neighbours[2] should be null");
	}
	
	@Test
	void initialGridInterconnection2()
	{
		Cell neighbour2 = aCell.neighbours[2];
		int cellCount = 0;
		for(int i = 0; i < neighbour2.neighbours.length; i++)
		{
			if(neighbour2.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 4, "aCell.neighbours[2] should be connected to 4 cells. Was connected to " + cellCount);
		assertTrue(neighbour2.neighbours[4] == neighbour2, "aCell.neighbours[2] should reference itself at it's neighbours[4].");
		assertTrue(neighbour2.neighbours[3] == aCell.neighbours[1], "aCell.neighbours[2] should reference aCell.neighbours[1] at index 3.");
		assertTrue(neighbour2.neighbours[6] == aCell.neighbours[4], "aCell.neighbours[2] should reference aCell.neighbours[4] at index 6.");
		assertTrue(neighbour2.neighbours[7] == aCell.neighbours[5], "aCell.neighbours[2] should reference aCell.neighbours[5] at index 7.");
		
		assertFalse(neighbour2.neighbours[2] != null, "neighbour0.neighbours[2] should be null");
	}
	
	@Test
	void initialGridInterconnection3()
	{
		Cell neighbour = aCell.neighbours[3];
		int cellCount = 0;
		for(int i = 0; i < neighbour.neighbours.length; i++)
		{
			if(neighbour.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 6, "aCell.neighbours[3] should be connected to 6 cells. Was connected to " + cellCount);
		assertTrue(neighbour.neighbours[4] == neighbour, "aCell.neighbours[3] should reference itself at it's neighbours[4].");
		assertTrue(neighbour.neighbours[1] == aCell.neighbours[0], "aCell.neighbours[3] should reference aCell.neighbours[0] at index 1.");
		assertTrue(neighbour.neighbours[2] == aCell.neighbours[1], "aCell.neighbours[3] should reference aCell.neighbours[1] at index 2.");
		assertTrue(neighbour.neighbours[5] == aCell, "aCell.neighbours[3] should reference aCell at index 5.");
		assertTrue(neighbour.neighbours[7] == aCell.neighbours[6], "aCell.neighbours[3] should reference aCell.neighbours[6] at index 7.");
		assertTrue(neighbour.neighbours[8] == aCell.neighbours[7], "aCell.neighbours[3] should reference aCell.neighbours[7] at index 8.");
		
		assertFalse(neighbour.neighbours[0] != null, "neighbour3.neighbours[0] should be null");
	}
	
	//Neighbour 4 is the original cell, and it's interconnections are already tested.
	
	@Test
	void initialGridInterconnection5()
	{
		Cell neighbour = aCell.neighbours[5];
		int cellCount = 0;
		for(int i = 0; i < neighbour.neighbours.length; i++)
		{
			if(neighbour.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 6, "aCell.neighbours[5] should be connected to 6 cells. Was connected to " + cellCount);
		assertTrue(neighbour.neighbours[4] == neighbour, "aCell.neighbours[5] should reference itself at it's neighbours[4].");
		assertTrue(neighbour.neighbours[0] == aCell.neighbours[1], "aCell.neighbours[5] should reference aCell.neighbours[0] at index 0.");
		assertTrue(neighbour.neighbours[1] == aCell.neighbours[2], "aCell.neighbours[5] should reference aCell.neighbours[2] at index 1.");
		assertTrue(neighbour.neighbours[3] == aCell, "aCell.neighbours[5] should reference aCell at index 3.");
		assertTrue(neighbour.neighbours[6] == aCell.neighbours[7], "aCell.neighbours[5] should reference aCell.neighbours[7] at index 6.");
		assertTrue(neighbour.neighbours[7] == aCell.neighbours[8], "aCell.neighbours[5] should reference aCell.neighbours[8] at index 7.");
		
		assertFalse(neighbour.neighbours[2] != null, "neighbour3.neighbours[0] should be null");
	}
	
	@Test
	void initialGridInterconnection6()
	{
		Cell neighbour = aCell.neighbours[6];
		int cellCount = 0;
		for(int i = 0; i < neighbour.neighbours.length; i++)
		{
			if(neighbour.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 4, "aCell.neighbours[6] should be connected to 4 cells. Was connected to " + cellCount);
		assertTrue(neighbour.neighbours[4] == neighbour, "aCell.neighbours[6] should reference itself at it's neighbours[4].");
		assertTrue(neighbour.neighbours[2] == aCell, "aCell.neighbours[6] should reference aCell at index 1.");
		assertTrue(neighbour.neighbours[1] == aCell.neighbours[3], "aCell.neighbours[6] should reference aCell.neighbours[5] at index 1.");
		assertTrue(neighbour.neighbours[5] == aCell.neighbours[7], "aCell.neighbours[6] should reference aCell.neighbours[7] at index 5.");
		
		assertFalse(neighbour.neighbours[0] != null, "neighbour0.neighbours[2] should be null");
	}
	
	@Test
	void initialGridInterconnection7()
	{
		Cell neighbour = aCell.neighbours[7];
		int cellCount = 0;
		for(int i = 0; i < neighbour.neighbours.length; i++)
		{
			if(neighbour.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 6, "aCell.neighbours[7] should be connected to 6 cells. Was connected to " + cellCount);
		assertTrue(neighbour.neighbours[4] == neighbour, "aCell.neighbours[5] should reference itself at it's neighbours[4].");
		assertTrue(neighbour.neighbours[0] == aCell.neighbours[3], "aCell.neighbours[7] should reference aCell.neighbours[3] at index 0.");
		assertTrue(neighbour.neighbours[2] == aCell.neighbours[5], "aCell.neighbours[7] should reference aCell.neighbours[5] at index 2.");
		assertTrue(neighbour.neighbours[1] == aCell, "aCell.neighbours[5] should reference aCell at index 1.");
		assertTrue(neighbour.neighbours[3] == aCell.neighbours[6], "aCell.neighbours[7] should reference aCell.neighbours[6] at index 3.");
		assertTrue(neighbour.neighbours[5] == aCell.neighbours[8], "aCell.neighbours[7] should reference aCell.neighbours[7] at index 8.");
		
		assertFalse(neighbour.neighbours[6] != null, "neighbour3.neighbours[0] should be null");
	}
	
	@Test
	void initialGridInterconnection8()
	{
		Cell neighbour8 = aCell.neighbours[8];
		int cellCount = 0;
		for(int i = 0; i < neighbour8.neighbours.length; i++)
		{
			if(neighbour8.neighbours[i] != null)
			{
				cellCount++;
			}
		}
		assertTrue(cellCount == 4, "aCell.neighbours[8] should be connected to 4 cells. Was connected to " + cellCount);
		assertTrue(neighbour8.neighbours[4] == neighbour8, "aCell.neighbours[8] should reference itself at it's neighbours[4].");
		assertTrue(neighbour8.neighbours[3] == aCell.neighbours[7], "aCell.neighbours[8] should reference aCell.neighbours[7] at index 3.");
		assertTrue(neighbour8.neighbours[1] == aCell.neighbours[5], "aCell.neighbours[8] should reference aCell.neighbours[5] at index 1.");
		assertTrue(neighbour8.neighbours[0] == aCell, "aCell.neighbours[8] should reference aCell at index 0.");
		
		assertFalse(neighbour8.neighbours[2] != null, "neighbour0.neighbours[2] should be null");
	}
	

}
