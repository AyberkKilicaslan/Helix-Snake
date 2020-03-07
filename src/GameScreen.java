import java.awt.Color;
import java.util.Random;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class GameScreen 
{
	static char [][] screenArray = new char[60][60];
	static int horizontal = 5;
	static int vertical = 5;
	static int levelCounter = 0;
	
	static void gameScreen()
	{
		for (int i = 0; i < 25; i++) 
		{
			for (int j = 0; j < 60; j++)
			{
				screenArray[i][j] = ' ';
			}
		}
		
		for (int i = 0; i < 25; i++) 
		{
			for (int j = 0; j < 60; j++)
			{
				screenArray[0][j] = '#';
				screenArray[24][j]= '#';
			}
			screenArray[i][0] = '#';
			screenArray[i][59] = '#';
		}
		
	}
	

	static void printScreen()
	{
		Enigma.getConsole().getTextWindow().setCursorPosition(64, Game.ypos+2);
		System.out.print("Last Trio : ");
		     Enigma.getConsole().getTextWindow().setCursorPosition(76, Game.ypos+2);
					System.out.print(MultiLinkedList.printCodon);
		 
		for (int i = 0; i <25; i++) 
		{
			for (int j = 0; j < 60; j++)
			{
				Enigma.getConsole().getTextWindow().setCursorPosition(j, i);
				if(screenArray[i][j] == '#')
				{
					Enigma.getConsole().setTextAttributes(new TextAttributes(Color.ORANGE));
					System.out.print(screenArray[i][j]);
				}
				if(screenArray[i][j] == 'A')
				{
					Enigma.getConsole().setTextAttributes(new TextAttributes(Color.CYAN));
					System.out.print(screenArray[i][j]);
				}
				if(screenArray[i][j] == 'T')
				{
					Enigma.getConsole().setTextAttributes(new TextAttributes(Color.MAGENTA));
					System.out.print(screenArray[i][j]);
				}
				if(screenArray[i][j] == 'C')
				{
					Enigma.getConsole().setTextAttributes(new TextAttributes(Color.BLUE));
					System.out.print(screenArray[i][j]);
				}
				if(screenArray[i][j] == 'G')
				{
					Enigma.getConsole().setTextAttributes(new TextAttributes(Color.GREEN));
					System.out.print(screenArray[i][j]);
				}
				
				
			}
			System.out.println();
			
		}
		Enigma.getConsole().getTextWindow().setCursorPosition(65, 0);
		System.out.println("Score : " + Game.finalScore);
		Enigma.getConsole().getTextWindow().setCursorPosition(65, 8);
		System.out.print("Level : " + levelCounter);
		
		
	}
	
	//every 20 sec. assign random sharp to the board
	static void randomAppear()
	{
		Random rnd = new Random();
		int horizontal = rnd.nextInt(56)+1;
		int vertical = rnd.nextInt(22)+1;
		while(screenArray[vertical][horizontal] != ' ')
		{
			horizontal = rnd.nextInt(56)+1;
			vertical = rnd.nextInt(22)+1;
		}
		screenArray[vertical][horizontal] = '#';
		System.out.println();
	}
	
	
	static void assignFirstRandomLetters()
	{
		Random rnd = new Random();
		
		for (int i = 0; i < 3; i++) 
		{
			int x_coord = rnd.nextInt(58)+1;
			int y_coord = rnd.nextInt(22)+1;
			int random = rnd.nextInt(4);
			
			if(random == 0)
				screenArray[y_coord][x_coord] = 'A';
			if(random == 1)
				screenArray[y_coord][x_coord] = 'T';
			if(random == 2)
				screenArray[y_coord][x_coord] = 'C';
			if(random == 3)
				screenArray[y_coord][x_coord] = 'G';
		}
		
		int horizontal = rnd.nextInt(56)+1;
		int vertical = rnd.nextInt(22)+1;
		while(screenArray[vertical][horizontal] != ' ')
		{
			horizontal = rnd.nextInt(56)+1;
			vertical = rnd.nextInt(22)+1;
		}
		screenArray[vertical][horizontal] = '#';
		
	}
	

	
	
	
}
