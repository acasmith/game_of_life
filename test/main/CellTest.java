package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {

	Cell aCell;
	
	/*
	 * Basic pre-test setup:
	 * Create a cell and give it an interconnected set of dead cell neighbours.
	 */
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
		this.aCell = new Cell();
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
	
	/**
	 * Algorithmic way of checking cell interconnections are correct, based on the logic used 
	 * in cell.interconnectNeighbours().
	 * Included here to make testing interconnections easier after interconnectNeighbours logic has been tested above.
	 * @param aCell the central cell around which the connections to be tested are based.
	 */
	private boolean neighbourInterconnectionTest(Cell aCell)
	{
		//Tracks whether any connections are not as expected.
		boolean connectionsCorrect = true;
		
		//For each cell in this.neighbours
		for(int currentElementIndex = 0; currentElementIndex < aCell.neighbours.length; currentElementIndex++)
		{
			//Calculate indexes of neighbours that are connected to current Element
			int lowBoundary = Math.max(currentElementIndex - 4, 0);
			int highBoundary = Math.min(currentElementIndex + 4, aCell.neighbours.length - 1);
			
			//Calculate new index relative to current element, based on OG index.
			for(int i = lowBoundary; i <= highBoundary; i++)
			{
				//Catch range values that are not adjacent to cell.
				if(Math.max(currentElementIndex % 3, i % 3) == 2 && 
						Math.min(currentElementIndex % 3, i % 3) == 0)
				{
					continue;
				}
				
				//Calculate shift and test to see if currentElement neighbour and aCell's neighbour is the same object.
				int difference = currentElementIndex - i;
				if(!(aCell.neighbours[currentElementIndex].neighbours[4 - difference] == aCell.neighbours[i]))
				{
					connectionsCorrect = false;
				}
			}
		}
		
		return connectionsCorrect;
	}
	
	/*
	 * Test algorithmic interconnection test above.
	 */
	@Test
	public void testNeighbourInterconnectionTest()
	{
		assertTrue(this.neighbourInterconnectionTest(this.aCell), "Algorithmic interconnection test should return true if manual permutation checks also return true.");
		aCell.neighbours[0].neighbours[5] = null;
		assertFalse(this.neighbourInterconnectionTest(this.aCell), "Algorithmic interconnection test should return false when cell interconnection is deliberately broken.");

	}
	
	@Test
	void doBirthNoNewNeighbours()
	{
		assertTrue(this.neighbourInterconnectionTest(this.aCell), "Initial check to ensure correct connection setup.");
		aCell.doBirth();
		assertTrue(this.neighbourInterconnectionTest(this.aCell), "Birthing cell should not have changed the neighbour interconnections.");
	}
	
	
	/*
	 * Sets up this.aCell.neighbours to have a certain number of nulls
	 */
	private void addNullNeighbours(int[] notNullIndexes)
	{
		Cell[] initialGrid = new Cell[9];
		for(int i = 0; i < notNullIndexes.length; i++)
		{
			if(notNullIndexes[i] == 4)
			{
				initialGrid[4] = this.aCell;
			}
			initialGrid[notNullIndexes[i]] = new Cell();
		}
		this.aCell.neighbours = initialGrid;
		this.aCell.interconnectNeighbours();
	}
	
	/*
	 * Counts nulls in a cells neighbours array.
	 */
	private int countNulls(Cell aCell)
	{
		int nullCount = 0;
		for(int i = 0; i < aCell.neighbours.length; i++)
		{
			if(!(aCell.neighbours[i] != null))
			{
				nullCount++;
			}
		}
		return nullCount;
	}
	
	@Test
	void doBirthOneNewNeighbour()
	{
		//Setup cell neighbours.
		int[] neighboursToAdd = {1,2,3,4,5,6,7,8};
		this.addNullNeighbours(neighboursToAdd);
		
		//Check neighbours has sufficient nulls
		int nullCount = this.countNulls(this.aCell);
		assertFalse(this.aCell.neighbours[0] != null, "Neighbour at index 0 should be null.");
		assertTrue(nullCount == 1, "There should be only one null in aCell.neighbours. Nulls found: " + nullCount);
		
		this.aCell.doBirth();
		
		//Check neighbours have been filled in correctly
		nullCount = this.countNulls(this.aCell);
		assertTrue(this.aCell.neighbours[0] != null, "Neighbour at index 0 should be null.");
		assertTrue(nullCount == 0, "There should be no nulls in aCell.neighbours. Nulls found: " + nullCount);
		assertTrue(this.neighbourInterconnectionTest(this.aCell), "Checking interconnection correct with new neighbour.");
	}
	
	void doBirthTwoNewNeighbour()
	{
		//Setup cell neighbours.
		int[] neighboursToAdd = {1,2,3,4,5,6,7};
		this.addNullNeighbours(neighboursToAdd);
		
		//Check neighbours has sufficient nulls
		int nullCount = this.countNulls(this.aCell);
		assertTrue(this.aCell.neighbours[0] != null, "Neighbour at index 0 should be null.");
		assertTrue(nullCount == 2, "There should be two nulls in aCell.neighbours. Nulls found: " + nullCount);
		
		this.aCell.doBirth();
		
		//Check neighbours have been filled in correctly
		nullCount = this.countNulls(this.aCell);
		assertTrue(this.aCell.neighbours[0] != null, "Neighbour at index 0 should be null.");
		assertTrue(nullCount == 0, "There should be no nulls in aCell.neighbours. Nulls found: " + nullCount);
		assertTrue(this.neighbourInterconnectionTest(this.aCell), "Checking interconnection correct with new neighbour.");
	}
	
	public void doBirthCoreTester(int[] neighboursToAdd)
	{
		this.addNullNeighbours(neighboursToAdd);
		
		//Check neighbours has sufficient nulls
		int nullCount = this.countNulls(this.aCell);
		assertTrue(nullCount == 8 - (neighboursToAdd.length - 1), "There should be " + (8 - (neighboursToAdd.length - 1)) + " nulls in aCell.neighbours. Nulls found: " + nullCount);
		
		this.aCell.doBirth();
		
		//Check neighbours have been filled in correctly
		nullCount = this.countNulls(this.aCell);
		assertTrue(nullCount == 0, "There should be no nulls in aCell.neighbours. Nulls found: " + nullCount);
		assertTrue(this.neighbourInterconnectionTest(this.aCell), "Checking interconnection correct with new neighbour.");
	}
	
	@Test
	void doBirthTestDriver()
	{
		int[] neighboursToAdd;
		for(int i = 0; i <= this.aCell.neighbours.length; i++)
		{
			neighboursToAdd = new int[i];
			for(int j = 0; j < i; j++)
			{
				neighboursToAdd[j] = j;
			}
			this.doBirthCoreTester(neighboursToAdd);
		}
	}
	
	
	//Check whether new cells correctly register as neighbours of other cells outside originCells neighbours.
	@Test 
	void doBirthNewCellPropogationIndexes0And2()
	{
		//Initial tests
		assertTrue(this.aCell.neighbours[0] != null);
		assertTrue(this.aCell.neighbours[2] != null);
		assertFalse(this.aCell.neighbours[0].neighbours[2] != null, "Neighbour[0] should not have a neighbour at index 2 yet.");
		assertFalse(this.aCell.neighbours[2].neighbours[0] != null, "Neighbour[2] should not have a neighbour at index 0 yet.");
		//Birth a new cell which should create neighbours that connect with neighbour[2]
		this.aCell.neighbours[0].doBirth();
		//Post operation tests
		assertTrue(this.aCell.neighbours[0] != null);
		assertTrue(this.aCell.neighbours[2] != null);
		assertTrue(this.aCell.neighbours[2].neighbours[0] != null, "Neighbour[2] should have a neighbour at index 0.");
		assertTrue(this.aCell.neighbours[0].neighbours[2] != null, "Neighbour[0] should have a neighbour at index 2.");
	}
	
	//Test whether the new cells birthed from two different origin cells get interconnected.
	@Test 
	void doBirthNewCellPropogationIndexes3And1()
	{
		//Initial tests
		assertFalse(this.aCell.neighbours[1].neighbours[0] != null, "Neighbour[1] should not have a neighbour at index 0 yet.");
		assertFalse(this.aCell.neighbours[3].neighbours[0] != null, "Neighbour[3] should not have a neighbour at index 0 yet.");
		//Birth a new cell which should create neighbours that connect with neighbour[2]
		this.aCell.neighbours[1].doBirth();
		this.aCell.neighbours[3].doBirth();
		//this.aCell.neighbours[0].doBirth();
		//Post operation tests
		assertTrue(this.aCell.neighbours[1].neighbours[0] != null, "Neighbour[1] should have a neighbour at index 0.");
		assertTrue(this.aCell.neighbours[1].neighbours[0].neighbours[6] != null, "Neighbour[1] should have a neighbour at neighbours[0].neighbours[6].");
		assertTrue(this.aCell.neighbours[3].neighbours[0].neighbours[2] != null, "Neighbour[3] should have a neighbour at neighbours[0].neighbours[2].");
		assertTrue(this.aCell.neighbours[3].neighbours[0] != null, "Neighbour[3] should have a neighbour at index 0.");
		assertTrue(this.aCell.neighbours[1].neighbours[0].neighbours[6] == this.aCell.neighbours[3].neighbours[0], 
				"The two new cells created by separate births should reference each other as neighbours.");
		assertTrue(this.aCell.neighbours[3].neighbours[0].neighbours[2] == this.aCell.neighbours[1].neighbours[0], 
				"The two new cells created by separate births should reference each other as neighbours.");
		assertTrue(this.aCell.neighbours[1].neighbours[0].neighbours[3] == this.aCell.neighbours[3].neighbours[0].neighbours[1], 
				"The two new cells should share common neighbours.");
	}
	
	public void testAllNeighbourCoords()
	{
		//Algorithmically test every value in range.
		int[] diffArray = {-1, 0, 1};
		for(int i = 0; i < 9; i++)
		{
			assertTrue(this.aCell.neighbours[i].getX() == this.aCell.getX() + diffArray[i % 3], "Neighbour at index " + i + " y coordinate should be " + (this.aCell.getY() + diffArray[i % 3]));
			assertTrue(this.aCell.neighbours[i].getY() == this.aCell.getY() + (diffArray[i / 3] * -1), "Neighbour at index " + i + " y coordinate should be " + (this.aCell.getY() + (diffArray[i / 3] * -1)));
		}
	}

	//Test coords are what you think they are.
	@Test 
	void checkOriginNeighbourCoords()
	{
		this.aCell = new Cell();
		assertTrue(this.aCell.getX() == 0, "Origin cell's x coordinate should be 0");
		assertTrue(this.aCell.getY() == 0, "Origin cell's y coordinate should be 0");
		
		this.aCell.makeOriginCell();
		
		//Manually test centre and range extremes.
		assertTrue(this.aCell.getX() == 0, "Origin cell's x coordinate should be 0");
		assertTrue(this.aCell.getY() == 0, "Origin cell's y coordinate should be 0");
		
		assertTrue(this.aCell.neighbours[0].getX() == -1, "Neighbours at index 0 x coordinate should be -1");
		assertTrue(this.aCell.neighbours[0].getY() == 1, "Neighbour at index 0 y coordinate should be 1");
		
		assertTrue(this.aCell.neighbours[8].getX() == 1, "Neighbours at index 8 x coordinate should be 1");
		assertTrue(this.aCell.neighbours[8].getY() == -1, "Neighbour at index 0 y coordinate should be -1");
		
		this.testAllNeighbourCoords();
		
	}
	
	@Test
	void checkNonOriginNeighbourCoords()
	{
		this.aCell = new Cell();
		this.aCell.makeOriginCell();
		this.aCell.neighbours[0].doBirth();
		this.aCell = this.aCell.neighbours[0];
		
		assertTrue(this.aCell.getX() == -1, "Starting cell for this test case should have x coordinate of -1.");
		assertTrue(this.aCell.getY() == 1, "Starting cell for this test case should have y coordinate of 1.");
		
		assertTrue(this.aCell.neighbours[2].getX() == (this.aCell.getX() + 1), "Neighbours at index 2 x coordinate should be " + (this.aCell.getX() + 1) + ", was " + (this.aCell.neighbours[2].getX()));
		assertTrue(this.aCell.neighbours[2].getY() == (this.aCell.getY() + 1), "Neighbours at index 2 y coordinate should be " + (this.aCell.getY() + 1) + ", was " + (this.aCell.neighbours[2].getY()));
		
		assertTrue(this.aCell.neighbours[6].getX() == (this.aCell.getX() -1), "Neighbours at index 6 x coordinate should be " + (this.aCell.getX() - 1) + ", was " + (this.aCell.neighbours[6].getX()));
		assertTrue(this.aCell.neighbours[6].getY() == (this.aCell.getY() -1), "Neighbours at index 6 y coordinate should be " + (this.aCell.getY() - 1) + ", was " + (this.aCell.neighbours[6].getY()));
		
		this.testAllNeighbourCoords();
	}
	
	@Test
	void testAliveNeighbourCounts()
	{
		
	}
	
}
