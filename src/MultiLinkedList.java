import enigma.core.Enigma;

public class MultiLinkedList {
	CategoryNode head;
	int arrayCount = 0;
	public void addCategory(String dataToAdd) {
		CategoryNode temp;
		if (head == null) {
			temp = new CategoryNode(dataToAdd);
			head = temp;
		} else {
			temp = head;
			while (temp.getDown() != null)
				temp = temp.getDown();
			CategoryNode newnode = new CategoryNode(dataToAdd);
			temp.setDown(newnode);
		}
	}

	public void addItem(String Category, String Item, int point) {
		if (head == null)
			System.out.println("Add a Category before Item");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				if (Category.equals(temp.getCategoryName())) {
					ItemNode temp2 = temp.getRight();
					if (temp2 == null) {
						temp2 = new ItemNode(Item, point);
						temp.setRight(temp2);
					} else {
						while (temp2.getNext() != null)
							temp2 = temp2.getNext();
						ItemNode newnode = new ItemNode(Item, point);

						temp2.setNext(newnode);
					}
				}
				temp = temp.getDown();
			}
		}
	}

	public int sizeCategory() {
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				count++;
				temp = temp.getDown();
			}
		}
		return count;
	}

	public int sizeItem() {
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				ItemNode temp2 = temp.getRight();
				while (temp2 != null) {
					count++;
					temp2 = temp2.getNext();
				}
				temp = temp.getDown();
			}
		}
		return count;
	}

	public void display() 
	{
		if (head == null)
			System.out.println("linked list is empty");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				System.out.print(temp.getCategoryName() + " ");
				ItemNode temp2 = temp.getRight();
				while (temp2 != null) {
					System.out.print(temp2.getItemName() + "-" + temp2.getPoint() + " ");
					temp2 = temp2.getNext();
				}
				temp = temp.getDown();
				System.out.println();
			}
		}
	}
	
	//I wrote the search function manually by using display func. for codon pointing
	static String printCodon = " ";
	public void search(String codon) 
	{
        if (head == null)
            System.out.println("linked list is empty");
        else {
            CategoryNode temp = head;
            while (temp != null) 
            {
            	
                ItemNode temp2 = temp.getRight();
                while (temp2 != null) 
                {

                    if (codon.equals(temp2.getItemName())) 
                    {
                    	if(temp.getCategoryName().equals("Stopcodons"))
                    	{
                    		Game.gameFlag = false;
                    	}
                    		
                    	else
                    	{
                    		 Game.finalScore += temp2.getPoint();
                             printCodon = (temp2.getItemName() + "-" + temp2.getPoint()+ "     ");
                             break;
                    	}
                       
                    } 
                    if(Game.gameFlag == false)
                    	break;
                    else
                        temp2 = temp2.getNext();
                }
                if(Game.gameFlag == false)
                	break;
                else
                {
                	 temp = temp.getDown();
                }
               
            }
            
        }
       
    }
	
}
