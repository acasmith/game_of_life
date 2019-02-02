package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameOfLifeTest {

	@Test
	void test0IterationsNoAliveCells() {
		int[] aliveIndexes = {};
		GameOfLife aGame = new GameOfLife(0);
		String result = aGame.startGame(aliveIndexes);
		assertTrue(result.equals("Game ended: all cells are dead."), "Game should end with statement: \"\"Game ended: all cells are dead.\" \"");
	}
	
	@Test
	void test0IterationsWithAliveCells()
	{
		int[] aliveIndexes = {4};
		GameOfLife aGame = new GameOfLife(0);
		String result = aGame.startGame(aliveIndexes);
		assertTrue(result.equals("Game ended: Max iterations (0) reached."), "Game should end with statement: \"Game ended: Max iterations (0) reached.\"");
	}
	
	@Test
	void test1IterationsWithNoAliveCells()
	{
		int[] aliveIndexes = {};
		GameOfLife aGame = new GameOfLife(1);
		String result = aGame.startGame(aliveIndexes);
		assertTrue(result.equals("Game ended: all cells are dead."), "Game should end with statement: \"Game ended: all cells are dead.\"");
	}
	
	@Test
	void test1IterationsWithAliveCells()
	{
		int[] aliveIndexes = {4};
		GameOfLife aGame = new GameOfLife(1);
		String result = aGame.startGame(aliveIndexes);
		System.out.println(result);
		assertTrue(result.equals("Game ended: all cells are dead."), "Game should end with statement: \"Game ended: all cells are dead.\"");
	}
	
	@Test
	void test1IterationsWith2AliveCells()
	{
		int[] aliveIndexes = {0, 8};
		GameOfLife aGame = new GameOfLife(1);
		String result = aGame.startGame(aliveIndexes);
		System.out.println(result);
		assertTrue(result.equals("Game ended: all cells are dead."), "Game should end with statement: \"Game ended: all cells are dead.\"");
	}
	
	@Test
	void test2IterationsWith2AliveCells()
	{
		int[] aliveIndexes = {4, 5};
		GameOfLife aGame = new GameOfLife(2);
		String result = aGame.startGame(aliveIndexes);
		System.out.println(result);
		assertTrue(result.equals("Game ended: all cells are dead."), "Game should end with statement: \"Game ended: all cells are dead.\"");
	}
	
	@Test
	void test2IterationsWith3AliveCells()
	{
		int[] aliveIndexes = {3, 4, 5};
		GameOfLife aGame = new GameOfLife(2);
		String result = aGame.startGame(aliveIndexes);
		System.out.println(result);
		assertTrue(result.equals("Game ended: all cells are dead."), "Game should end with statement: \"Game ended: all cells are dead.\"");
	}

	@Test
	void test2IterationsWithAllAliveCells()
	{
		int[] aliveIndexes = {0, 1, 2, 3, 4, 5};
		GameOfLife aGame = new GameOfLife(2);
		String result = aGame.startGame(aliveIndexes);
		System.out.println(result);
		assertTrue(result.equals("Game ended: all cells are dead."), "Game should end with statement: \"Game ended: all cells are dead.\"");
	}
}
