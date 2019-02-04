package main;

public class App 
{
	//Driver code to initialise game.
	public static void main(String[] args) 
	{
		int[] aliveIndexes = {1, 3};
		GameOfLife aGame = new GameOfLife(20);
		System.out.println(aGame.startGame(aliveIndexes));
	}
}
