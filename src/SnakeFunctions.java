import java.io.IOException;
import java.util.Random;

public class SnakeFunctions 
{
	static SingleLinkedList snakeList = new SingleLinkedList();
	
	//assigning first 3 letter to the snake
	static SingleLinkedList  randomFirst3()
	{
		snakeList = new SingleLinkedList();
		Random rnd = new Random();	
		int placex = 4;
		//erase last element after movement
		snakeList.addToHead(' ', placex++, 5);
		for (int i = 0; i < 3; i++) 
		{
			int randomfirst = rnd.nextInt(4);
			if(randomfirst == 0)
				snakeList.addToHead('A', placex++, 5);
			if(randomfirst == 1)
				snakeList.addToHead('T', placex++, 5);
			if(randomfirst == 2)
				snakeList.addToHead('C', placex++, 5);
			if(randomfirst == 3)
				snakeList.addToHead('G', placex++, 5);
		}
		
		 String stringSnake = SnakeFunctions.snakeList.display();
         String substringedSnake = stringSnake.substring(0, 3);
         String reverse = "";
         for(int i = substringedSnake.length() - 1; i >= 0; i--)
         {
             reverse  = reverse + substringedSnake.charAt(i);
         }
        // Aminoacids.textToMllAminos();
         Aminoacids.aminoacids.search(reverse);
		
		return snakeList;
	}
	
	//manual console clear
	static void consoleClear()
	{
		for (int i = 0; i <10; i++)
		{
			for (int j = 0; j < 20; j++)
			{
				
				System.out.println();
			}
		}
	}
	
	//after eating , assign random letter to the board
	static void generateLetterAfterEat()
	{
		Random rnd = new Random();
	 	int horizontal = rnd.nextInt(57)+1;
		int vertical = rnd.nextInt(22)+1;
		int random = rnd.nextInt(4);
		
		
		while(GameScreen.screenArray[vertical][horizontal] != ' ')
		{
			horizontal = rnd.nextInt(56)+2;
			vertical = rnd.nextInt(22)+1;
		}
		if(random == 0)
			GameScreen.screenArray[vertical][horizontal] = 'A';
		if(random == 1)
			GameScreen.screenArray[vertical][horizontal] = 'T';
		if(random == 2)
			GameScreen.screenArray[vertical][horizontal] = 'C';
		if(random == 3)
			GameScreen.screenArray[vertical][horizontal] = 'G';
	}

	//assigning snake list to the main board with coordinates
	static void placeSnakeToArray()
	{

		Node temp = SingleLinkedList.getHead();
		
		while (temp != null) 
		{
			GameScreen.screenArray[temp.getY_coordinate()][temp.getX_coordinate()] = (char) temp.getData();
			temp = temp.getLink();
		}
	}
	
	//movement functions for snake
	static void directionToTheLeft() throws NumberFormatException, IOException, InterruptedException
	{

		Node snakeHead = SingleLinkedList.getHead();

		snakeHead = SingleLinkedList.getHead();
		if (GameScreen.screenArray[snakeHead.getY_coordinate()][snakeHead.getX_coordinate() - 1] != ' ') {
			if (GameScreen.screenArray[snakeHead.getY_coordinate()][snakeHead.getX_coordinate() - 1] == '#')
				Game.gameFlag = false;
			else {
				Game.finalScore += 5;
				Game.eatCounter++;

				SnakeFunctions.snakeList.addToHead(
						GameScreen.screenArray[snakeHead.getY_coordinate()][snakeHead.getX_coordinate() - 1],
						snakeHead.getX_coordinate() - 1, snakeHead.getY_coordinate());
				GameScreen.screenArray[snakeHead.getY_coordinate()][snakeHead.getX_coordinate() - 1] = ' ';
				SnakeFunctions.generateLetterAfterEat();

				SnakeFunctions.placeSnakeToArray();
				if (Game.eatCounter == 3) {
					String SnakeString = SnakeFunctions.snakeList.display();
					String substringedSnake = SnakeString.substring(0, 3);
					String reverse = "";
					for (int i = substringedSnake.length() - 1; i >= 0; i--) {
						reverse = reverse + substringedSnake.charAt(i);
					}
					// Aminoacids.textToMllAminos();
					Aminoacids.aminoacids.search(reverse);
					Game.eatCounter = 0;
				}
			}

		}

		// holding coordinates foreach node
		int headtempX = 0, headtempY = 0, lastNodeX = 0, lastNodeY = 0;
		snakeHead = SingleLinkedList.getHead();
		int tempx = snakeHead.getX_coordinate();
		int tempy = snakeHead.getY_coordinate();
		snakeHead = snakeHead.getLink();

		// if snake eats itself
		while (snakeHead != null) {
			if (tempx == snakeHead.getX_coordinate() && tempy == snakeHead.getY_coordinate()) {
				Game.gameFlag = false;
			}

			snakeHead = snakeHead.getLink();
		}

		// assigning head again
		snakeHead = SingleLinkedList.getHead();
		while (snakeHead != null) {

			if (snakeHead == SingleLinkedList.getHead()) {

				headtempX = snakeHead.getX_coordinate();
				headtempY = snakeHead.getY_coordinate();
				snakeHead.setX_coordinate(snakeHead.getX_coordinate() - 1);

			} else {

				lastNodeX = snakeHead.getX_coordinate();
				lastNodeY = snakeHead.getY_coordinate();

				snakeHead.setX_coordinate(headtempX);
				snakeHead.setY_coordinate(headtempY);

				headtempX = lastNodeX;
				headtempY = lastNodeY;

			}

			snakeHead = snakeHead.getLink();
		}

	}
	
	static void directionToTheRight() throws NumberFormatException, IOException {

		Node snakeHead = SingleLinkedList.getHead();

		if (GameScreen.screenArray[snakeHead.getY_coordinate()][snakeHead.getX_coordinate() + 1] != ' ') {
			if (GameScreen.screenArray[snakeHead.getY_coordinate()][snakeHead.getX_coordinate() + 1] == '#')
				Game.gameFlag = false;
			else {
				Game.finalScore += 5;
				Game.eatCounter++;

				SnakeFunctions.snakeList.addToHead(
						GameScreen.screenArray[snakeHead.getY_coordinate()][snakeHead.getX_coordinate() + 1],
						snakeHead.getX_coordinate() + 1, snakeHead.getY_coordinate());
				GameScreen.screenArray[snakeHead.getY_coordinate()][snakeHead.getX_coordinate() + 1] = ' ';

				SnakeFunctions.generateLetterAfterEat();
				SnakeFunctions.placeSnakeToArray();
				if (Game.eatCounter == 3) {
					String SnakeString = SnakeFunctions.snakeList.display();
					String substringedSnake = SnakeString.substring(0, 3);
					String reverse = "";
					for (int i = substringedSnake.length() - 1; i >= 0; i--) {
						reverse = reverse + substringedSnake.charAt(i);
					}
					// Aminoacids.textToMllAminos();
					Aminoacids.aminoacids.search(reverse);
					Game.eatCounter = 0;
				}

			}
		}
		int tempX = 0, tempY = 0, lastNodeX = 0, lastNodeY = 0;
		snakeHead = SingleLinkedList.getHead();
		int tempx = snakeHead.getX_coordinate();
		int tempy = snakeHead.getY_coordinate();
		snakeHead = snakeHead.getLink();

		// if snake eats itself
		while (snakeHead != null) {
			if (tempx == snakeHead.getX_coordinate() && tempy == snakeHead.getY_coordinate())
				Game.gameFlag = false;

			snakeHead = snakeHead.getLink();
		}

		snakeHead = SingleLinkedList.getHead();
		while (snakeHead != null) {

			if (snakeHead == SingleLinkedList.getHead()) {

				tempX = snakeHead.getX_coordinate();
				tempY = snakeHead.getY_coordinate();

				snakeHead.setX_coordinate(snakeHead.getX_coordinate() + 1);

			} else {

				lastNodeX = snakeHead.getX_coordinate();
				lastNodeY = snakeHead.getY_coordinate();

				snakeHead.setX_coordinate(tempX);
				snakeHead.setY_coordinate(tempY);

				tempX = lastNodeX;
				tempY = lastNodeY;

			}

			snakeHead = snakeHead.getLink();
		}

	}

	static void directionToTheUp() throws NumberFormatException, IOException {

		Node snakeHead = SingleLinkedList.getHead();

		if (GameScreen.screenArray[snakeHead.getY_coordinate() - 1][snakeHead.getX_coordinate()] != ' ') {
			if (GameScreen.screenArray[snakeHead.getY_coordinate() - 1][snakeHead.getX_coordinate()] == '#')
				Game.gameFlag = false;
			else {
				Game.finalScore += 5;
				Game.eatCounter++;

				SnakeFunctions.snakeList.addToHead(
						GameScreen.screenArray[snakeHead.getY_coordinate() - 1][snakeHead.getX_coordinate()],
						snakeHead.getX_coordinate(), snakeHead.getY_coordinate() - 1);
				GameScreen.screenArray[snakeHead.getY_coordinate() - 1][snakeHead.getX_coordinate()] = ' ';
				SnakeFunctions.generateLetterAfterEat();
				SnakeFunctions.placeSnakeToArray();
				if (Game.eatCounter == 3) {
					String SnakeString = SnakeFunctions.snakeList.display();
					String substringedSnake = SnakeString.substring(0, 3);
					String reverse = "";
					for (int i = substringedSnake.length() - 1; i >= 0; i--) {
						reverse = reverse + substringedSnake.charAt(i);
					}
					// Aminoacids.textToMllAminos();
					Aminoacids.aminoacids.search(reverse);
					Game.eatCounter = 0;
				}

			}
		}
		snakeHead = SingleLinkedList.getHead();
		int tempx = snakeHead.getX_coordinate();
		int tempy = snakeHead.getY_coordinate();
		snakeHead = snakeHead.getLink();

		// if snake eats itself
		while (snakeHead != null) {
			if (tempx == snakeHead.getX_coordinate() && tempy == snakeHead.getY_coordinate())
				Game.gameFlag = false;

			snakeHead = snakeHead.getLink();
		}

		snakeHead = SingleLinkedList.getHead();
		int tempX = 0, tempY = 0, lastNodeX = 0, lastNodeY = 0;
		while (snakeHead != null) {

			if (snakeHead == SingleLinkedList.getHead()) {
				tempX = snakeHead.getX_coordinate();
				tempY = snakeHead.getY_coordinate();

				snakeHead.setY_coordinate(snakeHead.getY_coordinate() - 1);

			} else {

				lastNodeX = snakeHead.getX_coordinate();
				lastNodeY = snakeHead.getY_coordinate();

				snakeHead.setX_coordinate(tempX);
				snakeHead.setY_coordinate(tempY);

				tempX = lastNodeX;
				tempY = lastNodeY;

			}

			snakeHead = snakeHead.getLink();
		}

	}

	static void directionToTheDown() throws NumberFormatException, IOException {

		Node snakeHead = SingleLinkedList.getHead();

		if (GameScreen.screenArray[snakeHead.getY_coordinate() + 1][snakeHead.getX_coordinate()] != ' ') {
			// if snake hits the wall
			if (GameScreen.screenArray[snakeHead.getY_coordinate() + 1][snakeHead.getX_coordinate()] == '#')
				Game.gameFlag = false;
			// if snake eats letter
			else {
				Game.finalScore += 5;
				Game.eatCounter++;

				SnakeFunctions.snakeList.addToHead(
						GameScreen.screenArray[snakeHead.getY_coordinate() + 1][snakeHead.getX_coordinate()],
						snakeHead.getX_coordinate(), snakeHead.getY_coordinate() + 1);
				GameScreen.screenArray[snakeHead.getY_coordinate() + 1][snakeHead.getX_coordinate()] = ' ';
				SnakeFunctions.generateLetterAfterEat();
				SnakeFunctions.placeSnakeToArray();
				if (Game.eatCounter == 3) {
					String SnakeString = SnakeFunctions.snakeList.display();
					String substringedSnake = SnakeString.substring(0, 3);
					String reverse = "";
					for (int i = substringedSnake.length() - 1; i >= 0; i--) {
						reverse = reverse + substringedSnake.charAt(i);
					}

					// Aminoacids.textToMllAminos();
					Aminoacids.aminoacids.search(reverse);
					Game.eatCounter = 0;
				}

			}
		}
		snakeHead = SingleLinkedList.getHead();
		int tempx = snakeHead.getX_coordinate();
		int tempy = snakeHead.getY_coordinate();
		snakeHead = snakeHead.getLink();

		// if snake eats itself
		while (snakeHead != null) {
			if (tempx == snakeHead.getX_coordinate() && tempy == snakeHead.getY_coordinate())
				Game.gameFlag = false;

			snakeHead = snakeHead.getLink();
		}

		snakeHead = SingleLinkedList.getHead();
		int tempX = 0, tempY = 0, lastNodeX = 0, lastNodeY = 0;
		while (snakeHead != null) {

			if (snakeHead == SingleLinkedList.getHead()) {
				tempX = snakeHead.getX_coordinate();
				tempY = snakeHead.getY_coordinate();

				snakeHead.setY_coordinate(snakeHead.getY_coordinate() + 1);

			} else {

				lastNodeX = snakeHead.getX_coordinate();
				lastNodeY = snakeHead.getY_coordinate();

				snakeHead.setX_coordinate(tempX);
				snakeHead.setY_coordinate(tempY);

				tempX = lastNodeX;
				tempY = lastNodeY;

			}

			snakeHead = snakeHead.getLink();
		}

	}

}
