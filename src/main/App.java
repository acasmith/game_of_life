package main;

public class App 
{

	public static void main(String[] args) 
	{
		int[] aliveIndexes = {0, 8};
		GameOfLife aGame = new GameOfLife(0, aliveIndexes);
		System.out.println("Hello World!");
		aGame.aBoard.aliveCells.get(0).neighbours[0].doBirth();
		aGame.aBoard.aliveCells.add(aGame.aBoard.aliveCells.get(0).neighbours[0]);
		
		aGame.aBoard.aliveCells.get(1).neighbours[8].doBirth();
		aGame.aBoard.aliveCells.add(aGame.aBoard.aliveCells.get(1).neighbours[8]);
		aGame.printGameBoard();
	}

}
