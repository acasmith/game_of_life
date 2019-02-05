package main;

public class App 
{
	//Driver code to initialise game.
	//Contains validation code that results in program exit on failure.
	//GameOfLife class also contains some validation code, but this does not result in a program exit.
	public static void main(String[] args) 
	{
		try
		{
			int[] numberOfIterations = App.getIntegers(args, 0, Math.min(1, args.length));
			int[] initialAliveIndexes = App.getIntegers(args, Math.min(1, args.length), args.length);
			boolean validationFailed = false;
			
			//Validate numberOfIterations
			if(numberOfIterations.length == 0 || numberOfIterations[0] < 0)
			{
				validationFailed = true;
			}
			
			//Validate initialAliveIndexes
			if(initialAliveIndexes.length > 0)
			{
				for(int aliveIndex : initialAliveIndexes)
				{
					if(aliveIndex < 0 || aliveIndex > 9)
					{
						validationFailed = true;
					}
				}
			}
			
			//If inputs passed validation run the game.
			if(validationFailed)
			{
				System.out.println("Please ensure the number of iterations is an integer >= 0, "
									+ "and that any indexes for the initial alive cells are integers "
									+ "within the range 0-8 inclusive.");
			}
			else
			{
				GameOfLife aGame = new GameOfLife(numberOfIterations[0]);
				System.out.println(aGame.startGame(initialAliveIndexes));
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Some of those game parameters where not integers or were too large an integer. "
								+ "Please make sure all the parameters you enter are "
								+ "positive integers and that initial alive indexesa are "
								+ "within the range 0 -8 inclusive.");
		}
		
		/* Basic template code if you want to hardcode parameters. Comment out the try-catch above before running.
		 * 	int numberOfIterations = 5; //Set the number of iterations
			int[] initialAliveIndexes = {1, 3};	//Set the indexes of the alive cells on the initial board.
			GameOfLife aGame = new GameOfLife(numberOfIterations);
			System.out.println(aGame.startGame(initialAliveIndexes));
		*/
	}
	
	/*
	 * Method to convert string array into an integer one.
	 * Start index inclusive, end index exclusive.
	 */
	private static int[] getIntegers(String[] stringArray, int startIndex, int endIndex)
	{
		int[] results = new int[endIndex - startIndex];
		
		for(int i = 0; i < endIndex - startIndex; i++)
		{
			results[i] = Integer.parseInt(stringArray[startIndex + i]);
		}
		
		return results;
	}
}
