import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class SnakeMain {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception 
	{
		
		Enigma.getConsole("Game", 150, 50, 25, 0).setTextAttributes(new TextAttributes(Color.GREEN,Color.BLACK));
		Game snakeGame = new Game();
		
	}

}
