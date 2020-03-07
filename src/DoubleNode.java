
public class DoubleNode 
{
	private Object data;
    private DoubleNode prev; 
    private DoubleNode next;
    private String name;

   public DoubleNode(Object dataToAdd,String name) {
     data = dataToAdd;
     prev = null;
     next = null;
     this.name=name;
   }
	   
   public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Object getData() {
     return data;
   }

   public void setData(Object data) {
     this.data = data;
   }

   public DoubleNode getNext() {
     return next; 
   }

   public void setNext(DoubleNode next) {
     this.next = next;
   }
   
   public DoubleNode getPrev() {
	 return prev; 
   }

   public void setPrev(DoubleNode prev) {
     this.prev = prev;
   }
}
