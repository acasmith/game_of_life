package main;

public class App 
{

	public static void main(String[] args) 
	{
		int[] aliveIndexes = {};
		GameOfLife aGame = new GameOfLife(0);
		System.out.println(aGame.startGame(aliveIndexes));
	}
}
