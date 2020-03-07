import enigma.core.Enigma;

public class SingleLinkedList {

	enigma.console.Console cn = Enigma.getConsole("HELIX SNAKE", 100, 30, 20, 5);

	private static Node head;

	public SingleLinkedList() {

		head = null;

	}

	public static Node getHead() {
		return head;
	}

	public static void setHead(Node head) {
		SingleLinkedList.head = head;
	}

	public void addToHead(Object data, int x_coordinate, int y_coordinate) {

		Node newNode = new Node(data, x_coordinate, y_coordinate);

		if (head == null) {
			head = newNode;
		} else {

			Node temp = head;

			newNode.setLink(head);

			head = newNode;

		}

	}

	public int search(Object dataToSearch) {

		int counter = 0;
		Node temp = head;

		while (temp != null) {
			if ((int) temp.getData() == (int) dataToSearch) {

				counter++;

			}
			temp = temp.getLink();
		}
		return counter;

	}

	public void remove(Object dataToRemove) {

		if (head == null)
			System.err.println("Linked List is Empty");
		else {
			while (head != null && (int) head.getData() == (int) dataToRemove)
				head = head.getLink();
			Node temp = head;
			Node prev = null;
			while (temp != null) {
				if ((int) temp.getData() == (int) dataToRemove) {
					prev.setLink(temp.getLink());
					temp = temp.getLink();
				} else {
					prev = temp;
					temp = temp.getLink();
				}
			}

		}

	}

	public String display() {

		String output = "";
		Node temp = head;
		while (temp != null) {
			output += temp.getData();
			temp = temp.getLink();
		}
		return output;
	}

	public int size() {

		Node temp = head;
		int counter = 0;
		while (temp != null) {
			counter++;
			temp = temp.getLink();
		}
		return counter;

	}

}