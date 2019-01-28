package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTest {
	
	@Test
	void testInitialNullArray()
	{
		Cell aCell = new Cell();
		assertFalse(aCell.neighbours[0] != null);
	}

}
