
public class DoubleLinkedList 
{
	static DoubleNode head;	
	static DoubleNode tail;

	public DoubleLinkedList() {
		head = null;
		tail = null;
	}
 
	public void Add(Integer dataToAdd,String name) 
	{
		
		DoubleNode newnode;
		
		if (head == null) 
		{  
			newnode = new DoubleNode(dataToAdd, name); 
			head = newnode;
			tail = newnode;	     
		}
           else 
            {
        	   newnode = new DoubleNode(dataToAdd,name); 
        	   if (dataToAdd > (int) head.getData()) 
         	   {
                    newnode.setNext(head);
                    head.setPrev(newnode);
                    head=newnode;
                }
        	   else
        	   {
                DoubleNode temp = head;
                while (temp.getNext() != null && dataToAdd < (int) temp.getNext().getData()) 
                {
                    temp = temp.getNext();
                }
                
                newnode.setPrev(temp);
                newnode.setNext(temp.getNext());
                if (temp.getNext() != null) // adding between DoubleNodes
                    temp.getNext().setPrev(newnode);
                else // adding to the end
                    tail = newnode;
                temp.setNext(newnode);
        	   }

            }

        }
	public void AddSwapped(String name,int dataToAdd) 
	{
		
		DoubleNode newnode;
		
		if (head == null) 
		{  
			newnode = new DoubleNode(dataToAdd, name); 
			head = newnode;
			tail = newnode;	     
		}
           else 
            {
        	   newnode = new DoubleNode(dataToAdd,name); 
        	   if (dataToAdd > (int) head.getData()) 
         	   {
                    newnode.setNext(head);
                    head.setPrev(newnode);
                    head=newnode;
                }
        	   else
        	   {
                DoubleNode temp = head;
                while (temp.getNext() != null && dataToAdd < (int) temp.getNext().getData()) 
                {
                    temp = temp.getNext();
                }
                
                newnode.setPrev(temp);
                newnode.setNext(temp.getNext());
                if (temp.getNext() != null) // adding between DoubleNodes
                    temp.getNext().setPrev(newnode);
                else // adding to the end
                    tail = newnode;
                temp.setNext(newnode);
        	   }

            }

        }
	public void remove(Integer s)
	{		
		if (head == null)    
			System.out.println("linked list is empty");
		else   
		{
			while (((Integer) head.getData()).equals(s))	{	   
				head = head.getNext();
				head.setPrev(null);
			}
			DoubleNode temp = head;
			while (temp != null)
			{
				if (((Integer)temp.getData()).equals(s)) {
					if (temp.getNext() == null) {
						tail = tail.getPrev();
						tail.setNext(null);
					}
					else {
						temp.getPrev().setNext(temp.getNext());
						temp.getNext().setPrev(temp.getPrev());
					}		    			
				}
				temp=temp.getNext();
			}    	
		}
	}

	public int size()
	{
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			DoubleNode temp = head;
			while (temp != null)
			{
				count++;
				temp=temp.getNext();
			}
		}
		return count;   
	}

	public void display()
	{
		int counter = 1;
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			DoubleNode temp = head;
			while (temp != null)
			{
				if(counter==11)
					break;
				else
				{
					if(counter < 10)
					{
						
						System.out.print("      " + counter++ + " )" +temp.getName()+ "        " +temp.getData());
						System.out.println();
						temp = temp.getNext();
						
					}
					else
					{
						System.out.print("      " + counter++ + ")" +temp.getName()+ "        " +temp.getData());
						System.out.println();
						temp = temp.getNext();
					}
						
					
				}
			}
			System.out.println();
		}
	}	
	
	

	public boolean search(Integer s)
	{
		boolean flag = false;
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			DoubleNode temp = head;
			while (temp != null)
			{
				if (temp.getData().equals(s)) {
					flag = true;
					break;
				}
				temp = temp.getNext();
			}	    
		}
		return flag;
	}

	
	public Object getElement(int x)
	{
		boolean flag = false;
		if (head == null)    
		{
			System.out.println("linked list is empty");
			return null;
		}
		else if(x > size())
		{
			System.out.println("index is out of range");
			return null;
		}
		else {
			DoubleNode temp = head;
			int count = 1;
			while (temp != null)
			{
				if (count == x) {
					return temp.getData();
				}
				temp = temp.getNext();
				count++;
			}	    
		}
		return null;
	}
}
