package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTest {
	
	@Test
	void testInitialNullArray()
	{
		Cell aCell = new Cell();
		boolean isNull = true;
		for(int i = 0; i < aCell.neighbours.length; i++)
		{
			isNull = !(aCell.neighbours[i] != null);
		}
		assertTrue(isNull, "A Cells initial neighbours array should all be null.");
	}
	

}
