import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;

public class Game {
	public enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard");
	public TextMouseListener tmlis;
	public KeyListener klis;

	// for direction
	int turnChoice;
	// set cursor y coord
	static int ypos = 1;
	static boolean repeatFlag = true;
	static boolean gameFlag = true;
	static int finalScore = 0;
	static int threadSpeed = 100;
	// level decider
	static double sharpSpeedHolder;
	// eating counter
	static int eatCounter = 0;
	// Taking user name
	static String nameInput = "";
	static int timeCounter=0;
	static DoubleLinkedList scoreHolder = new DoubleLinkedList();
	
	// ------ Standard variables for mouse and keyboard ------
	public int mousepr; // mouse pressed?
	public int mousex, mousey; // mouse text coords.
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)<
	// ----------------------------------------------------

	

	

	// manual keylistener which we wrote
	public int keyListener() {

		if (keypr == 1) {
			// blocking turn left if snake is moving to the right
			if (turnChoice != 1) {
				if (rkey == KeyEvent.VK_LEFT) {
					turnChoice = 2;

				}
			}

			// blocking turn right if snake is moving to the left
			if (turnChoice != 2) {
				if (rkey == KeyEvent.VK_RIGHT) {
					turnChoice = 1;
				}
			}

			// blocking turn down if snake is moving to the up
			if (turnChoice != 4) {
				if (rkey == KeyEvent.VK_UP) {
					turnChoice = 3;
				}
			}

			// blocking turn up if snake is moving to the down
			if (turnChoice != 3) {
				if (rkey == KeyEvent.VK_DOWN) {
					turnChoice = 4;
				}
			}

			keypr = 0;
		}

		return turnChoice;
	}

	public void readScoreBoardText() throws IOException
	{
		File file1 = new File("HighScoreTable.txt");
		BufferedReader br2 = new BufferedReader(new FileReader(file1));
		String st2;
		while ((st2 = br2.readLine()) != null) {
			String[] score_name = st2.split(";");
			scoreHolder.Add(Integer.valueOf(score_name[1]), score_name[0]);
			;
		}

		try 
		{
			File file = new File("ScoreTable.txt");

			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			while ((st = br.readLine()) != null) {
				String[] score_name = st.split(" ");
				scoreHolder.Add(Integer.valueOf(score_name[0]), score_name[1]);
				;
			}
		}

		catch (Exception e) {
			
		}

	}

	public void gameLoop() throws InterruptedException, NumberFormatException, IOException 
	{
		turnChoice = 1;
		scoreHolder = new DoubleLinkedList();
		
		//time variables for sharp
		long time1 = 0;
		long time2 = System.currentTimeMillis();
		//time variables for counting time
		long time3 = 0;
		long time4 = System.currentTimeMillis();
		
		Enigma.getConsole().getTextWindow().setCursorPosition(0, 0);;
		System.out.println(
				" __    __            __  __                   ______                       __                 \r\n"
						+ "|  \\  |  \\          |  \\|  \\                 /      \\                     |  \\                \r\n"
						+ "| $$  | $$  ______  | $$ \\$$ __    __       |  $$$$$$\\ _______    ______  | $$   __   ______  \r\n"
						+ "| $$__| $$ /      \\ | $$|  \\|  \\  /  \\      | $$___\\$$|       \\  |      \\ | $$  /  \\ /      \\ \r\n"
						+ "| $$    $$|  $$$$$$\\| $$| $$ \\$$\\/  $$       \\$$    \\ | $$$$$$$\\  \\$$$$$$\\| $$_/  $$|  $$$$$$\\\r\n"
						+ "| $$$$$$$$| $$    $$| $$| $$  >$$  $$        _\\$$$$$$\\| $$  | $$ /      $$| $$   $$ | $$    $$\r\n"
						+ "| $$  | $$| $$$$$$$$| $$| $$ /  $$$$\\       |  \\__| $$| $$  | $$|  $$$$$$$| $$$$$$\\ | $$$$$$$$\r\n"
						+ "| $$  | $$ \\$$     \\| $$| $$|  $$ \\$$\\       \\$$    $$| $$  | $$ \\$$    $$| $$  \\$$\\ \\$$     \\\r\n"
						+ " \\$$   \\$$  \\$$$$$$$ \\$$ \\$$ \\$$   \\$$        \\$$$$$$  \\$$   \\$$  \\$$$$$$$ \\$$   \\$$  \\$$$$$$$");
		
		System.out.println();
		System.out.println("Press enter to continue !");
		Scanner enter = new Scanner(System.in);
		enter.nextLine();
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your name please !");
		nameInput = input.nextLine();

		System.out.println("1 - Easy");
		System.out.println("2 - Medium");
		System.out.println("3 - Hard");
		System.out.println("4 - Expert");
		System.out.println("5 - Impossible");
		System.out.println("6 - Manual speed");
		System.out.println("Choose your level !!");
		System.out.print("Choose : ");
		double speedInt = input.nextDouble();

		// Level decision
		if (speedInt == 1)
			sharpSpeedHolder = 20000;
		if (speedInt == 2)
			sharpSpeedHolder = 10000;
		if (speedInt == 3)
			sharpSpeedHolder = 5000;
		if (speedInt == 4)
			sharpSpeedHolder = 500;
		if (speedInt == 5)
			sharpSpeedHolder = 1;
		if (speedInt == 6) {
			System.out.println("Enter sharp appear per second !");
			speedInt = input.nextDouble();
			sharpSpeedHolder = speedInt * 1000;
		}

		try 
		{
			readScoreBoardText();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SnakeFunctions.snakeList = new SingleLinkedList();
		SnakeFunctions.randomFirst3();
		GameScreen.gameScreen();
		GameScreen.assignFirstRandomLetters();

		while (gameFlag) 
		{
			
			time1 = System.currentTimeMillis();
			if (time1 - time2 >= sharpSpeedHolder) 
			{
				GameScreen.randomAppear();
				time2 = time1;
				GameScreen.levelCounter++;
			}
			
			
			time3 = System.currentTimeMillis();
			if (time3 - time4 >= 1000) 
			{
				time4 = time3;
				timeCounter++;
			}
			Enigma.getConsole().getTextWindow().setCursorPosition(65, 14);
			System.out.println("Time : " + timeCounter);
			
			
			
			
			SnakeFunctions.placeSnakeToArray();

			GameScreen.printScreen();

			if (keyListener() == 1)
				SnakeFunctions.directionToTheRight();
			if (keyListener() == 2)
				SnakeFunctions.directionToTheLeft();
			if (keyListener() == 3)
				SnakeFunctions.directionToTheUp();
			if (keyListener() == 4)
				SnakeFunctions.directionToTheDown();

			// if u want to move snake open it
			// turnChoice = 0;

			Thread.sleep(threadSpeed);
			SnakeFunctions.consoleClear();

		}
		// adding score board final score and player's name
		scoreHolder.Add(finalScore, nameInput);

		// writing informations to the text file after game finished
		try 
		{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ScoreTable.txt", true)));
			out.println(finalScore + " " + nameInput);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Game() throws Exception { // --- Contructor
		
		// ------ Standard code for mouse and keyboard ------ Do not change
		tmlis = new TextMouseListener() {
			public void mouseClicked(TextMouseEvent arg0) {
			}

			public void mousePressed(TextMouseEvent arg0) {

				if (mousepr == 0) {
					mousepr = 1;
					mousex = arg0.getX();
					mousey = arg0.getY();
				}
			}

			public void mouseReleased(TextMouseEvent arg0) {
			}
		};
		cn.getTextWindow().addTextMouseListener(tmlis);

		klis = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {

				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		// ----------------------------------------------------
		Aminoacids.textToMllAminos();
		while (repeatFlag) 
		{
			gameLoop();

			if (gameFlag == false) 
			{
				Enigma.getConsole().getTextWindow().setCursorPosition(0, 1);
				System.out.println("Game Over !!");
				System.out.print(nameInput + "'s" + " final score is : ");
				System.out.println(finalScore);
				System.out.println();
				System.out.println("***********Top 10 Score***********");
				Enigma.getConsole().getTextWindow().setCursorPosition(7, 5);
				System.out.println("Ranking");
				Enigma.getConsole().getTextWindow().setCursorPosition(18, 5);
				System.out.println("Score");
				scoreHolder.display();
				System.out.println("Do you want to play again ?  Y/N ");
				Scanner repeat = new Scanner(System.in);
				String repeatGame = repeat.nextLine();
				if (repeatGame.equalsIgnoreCase("N")) 
				{
					System.out.println("Do you want to exit ?");
					String quit = repeat.nextLine();
					if (quit.equalsIgnoreCase("Y"))
					{
						repeatFlag = false;
						System.exit(0);
					}
					else
					{
						finalScore = 0;
						GameScreen.levelCounter=0;
						timeCounter = 0;
						SnakeFunctions.consoleClear();
						repeatFlag = true;
						gameFlag = true;
					}
						
				}
				else
				{
					finalScore = 0;
					GameScreen.levelCounter=0;
					timeCounter = 0;
					SnakeFunctions.consoleClear();
					repeatFlag = true;
					gameFlag = true;
				}
				
				
				
			}

		}

	}

}
