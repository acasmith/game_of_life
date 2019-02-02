package main;

public class App 
{

	public static void main(String[] args) 
	{
		int[] aliveIndexes = {0, 3, 4, 8};
		GameOfLife aGame = new GameOfLife(1);
		System.out.println(aGame.startGame(aliveIndexes));
	}
}
