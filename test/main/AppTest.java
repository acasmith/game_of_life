package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
	
	int numberOfIterations;
	int[] aliveIndexes;
	OutputStream os;
	PrintStream ps;
	final PrintStream originalOut = System.out;
	

	@BeforeEach
	public void initialize()
	{
		os = new ByteArrayOutputStream();
		ps = new PrintStream(os);
		System.setOut(ps);
	}
	
	@After
	public void tearDown()
	{
		ps.close();
		System.setOut(this.originalOut);
		
	}
	
	//No args
	@Test
	public void mainNoArguments()
	{
		String targetString = "Please ensure the number of iterations is an integer >= 0, "
							+ "and that any indexes for the initial alive cells are integers "
							+ "within the range 0-8 inclusive.\r\n";
		
		App.main(new String[0]);
		String outString = os.toString();
		
		assertTrue(outString.length() == targetString.length(), "target size: " + targetString.length() + ", outString size: " + outString.length());
		assertTrue(outString.equals(targetString), "Console output should equal the target string. Instead was: \"" + outString + "\"");
	}
	
	//Negative iterations
	@Test
	public void mainNegativeIterations()
	{
		String targetString = "Please ensure the number of iterations is an integer >= 0, "
							+ "and that any indexes for the initial alive cells are integers "
							+ "within the range 0-8 inclusive.\r\n";
		String[] args = new String[1];
		args[0] = "-1";
		
		App.main(args);
		String outString = os.toString();
		
		assertTrue(outString.length() == targetString.length(), "target size: " + targetString.length() + ", outString size: " + outString.length());
		assertTrue(outString.equals(targetString), "Console output should equal the target string. Instead was: \"" + outString + "\"");
	}
	
	//Too large iterations
	@Test
	public void mainVeryLargeIterations()
	{
		String targetString = "Some of those game parameters where not integers or were too large an integer. "
							+ "Please make sure all the parameters you enter are "
							+ "positive integers and that initial alive indexesa are "
							+ "within the range 0 -8 inclusive.\r\n";
		String[] args = new String[1];
		args[0] = "400000000000000000";
		
		App.main(args);
		String outString = os.toString();
		
		assertTrue(outString.length() == targetString.length(), "target size: " + targetString.length() + ", outString size: " + outString.length());
		assertTrue(outString.equals(targetString), "Console output should equal the target string. Instead was: \"" + outString + "\"");
	}
	
	//Wrong type
	@Test
	public void mainWrongTypeIterations()
	{
		String targetString = "Some of those game parameters where not integers or were too large an integer. "
							+ "Please make sure all the parameters you enter are "
							+ "positive integers and that initial alive indexesa are "
							+ "within the range 0 -8 inclusive.\r\n";
		String[] args = new String[1];
		args[0] = "a";
		
		App.main(args);
		String outString = os.toString();
		
		assertTrue(outString.length() == targetString.length(), "target size: " + targetString.length() + ", outString size: " + outString.length());
		assertTrue(outString.equals(targetString), "Console output should equal the target string. Instead was: \"" + outString + "\"");
	}
	
	//Correct iteration
	@Test
	public void mainValidIterationsNoAliveIndexes()
	{
		String targetString = "Initial board: \n" +
								"All cells are dead.\r\n" +
								"Game ended: all cells are dead.\r\n";
		String[] args = new String[1];
		args[0] = "1";
		
		App.main(args);
		String outString = os.toString();
		
		assertTrue(outString.length() == targetString.length(), "target size: " + targetString.length() + ", outString size: " + outString.length());
		assertTrue(outString.equals(targetString), "Console output should equal the target string. Instead was: \"" + outString + "\"");
	}
	
	//Out of range alive indexes (0-8)
	@Test
	public void mainOutOfRangeAliveIndexes()
	{
		String targetString = "Please ensure the number of iterations is an integer >= 0, "
							+ "and that any indexes for the initial alive cells are integers "
							+ "within the range 0-8 inclusive.\r\n";
		String[] args = new String[3];
		args[0] = "1";
		args[1] = "-1";
		args[2] = "9";
		
		App.main(args);
		String outString = os.toString();
		
		assertTrue(outString.length() == targetString.length(), "target size: " + targetString.length() + ", outString size: " + outString.length());
		assertTrue(outString.equals(targetString), "Console output should equal the target string. Instead was: \"" + outString + "\"");
	}
	
	//Too big alive indexes
	@Test
	public void mainTooBigAliveIndexes()
	{
		String targetString = "Some of those game parameters where not integers or were too large an integer. "
				+ "Please make sure all the parameters you enter are "
				+ "positive integers and that initial alive indexesa are "
				+ "within the range 0 -8 inclusive.\r\n";
		
		String[] args = new String[3];
		args[0] = "1";
		args[1] = "-10000000000000000000";
		args[2] = "9";
		
		App.main(args);
		String outString = os.toString();
		
		assertTrue(outString.length() == targetString.length(), "target size: " + targetString.length() + ", outString size: " + outString.length());
		assertTrue(outString.equals(targetString), "Console output should equal the target string. Instead was: \"" + outString + "\"");
	}
	
	//Wrong type alive indexes
	@Test
	public void mainWrongTypeAliveIndexes()
	{
		String targetString = "Some of those game parameters where not integers or were too large an integer. "
				+ "Please make sure all the parameters you enter are "
				+ "positive integers and that initial alive indexesa are "
				+ "within the range 0 -8 inclusive.\r\n";
		
		String[] args = new String[3];
		args[0] = "1";
		args[1] = "7";
		args[2] = "nine";
		
		App.main(args);
		String outString = os.toString();
		
		assertTrue(outString.length() == targetString.length(), "target size: " + targetString.length() + ", outString size: " + outString.length());
		assertTrue(outString.equals(targetString), "Console output should equal the target string. Instead was: \"" + outString + "\"");
	}
	
	//Valid alive indexes.
	@Test
	public void mainAllValidArgs()
	{
		String targetString = "Initial board: \n" + 
				"  0 1 \n" + 
				"0| |A|\n" + 
				"1|0| |\n" + 
				"\r\n" + 
				"Turn 1: \n" + 
				"All cells are dead.\n" + 
				"\r\n" + 
				"Game ended: all cells are dead.\r\n";
		
		String[] args = new String[3];
		args[0] = "1";
		args[1] = "4";
		args[2] = "2";
		
		App.main(args);
		String outString = os.toString();
		
		assertTrue(outString.length() == targetString.length(), "target size: " + targetString.length() + ", outString size: " + outString.length());
		assertTrue(outString.equals(targetString), "Console output should equal the target string. Instead was: \"" + outString + "\"");
	}
	
}
