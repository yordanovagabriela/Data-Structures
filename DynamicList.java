public class DynamicList {

	private class Node {
		Object element;
		Node next;

		Node(Object element, Node prevNode) {
			this.element = element;
			prevNode.next = this;
		}

		Node(Object element) {
			this.element = element;
			next = null;
		}
	}

	private Node head;
	private Node tail;
	private int count;

	public DynamicList() {
		this.head = null;
		this.tail = null;
		this.count = 0;
	}

	public void add(Object item) {
		if(head == null) {
			head = new Node(item);
			tail = head;
		} else {
			Node newNode = new Node(item, tail);
			tail = newNode;
		}
		count++;
	}

	public Object remove(int index) {
		if(index >= count || index > 0) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		int currentIndex = 0;
		Node currentNode = head;
		Node prevNode = null;

		while(currentIndex < index) {
			prevNode = currentNode;
			currentNode = currentNode.next;
			currentIndex++;
		}

		count--;
		if(count == 0) {
			head = null;
			tail = null;
		} else if (prevNode == null) {
			head = currentNode.next;
		} else {
			prevNode.next = currentNode.next;
		}
		return currentNode.element;
	}

	public int remove(Object item) {
		int currentIndex = 0;
		Node currentNode = head;
		Node prevNode = null;
		while(currentNode != null) {
			if((currentNode.element != null && currentNode.element.equals(item)) || (currentNode.element == null) && (item == null)) {
				break;
			}
			prevNode = currentNode;
			currentNode = currentNode.next;
			currentIndex++;
		}

		if(currentNode != null) {
			count--;
			if(count == 0) {
				head = null;
				tail = null;
			} else if(prevNode == null) {
				head = currentNode.next;
			} else {
				prevNode.next = currentNode.next;
			}
			return currentIndex;
		} else {
			return -1;
		}
	}

	public int indexOf(Object item) {
		int index = 0;
		Node current = head;
		while(current != null) {
			if((current.element != null && current.element.equals(item)) || (current.element == null) && (item == null)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	public boolean contains(Object item) {
		int index = indexOf(item);
		boolean found = (index != -1);
		return found;
	}

	public Object elementAt(int index) {
		if(index >= count || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		Node currentNode = this.head;
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode.element;
	}

	public int getLength() {
		return count;
	}

	public static void main(String[] args) {
		DynamicList shoppingList = new DynamicList();
		shoppingList.add("Milk");
		shoppingList.add("Honey");
		shoppingList.add("Olives");
		shoppingList.add("Beer");
		shoppingList.remove("Olives");
		System.out.println("We need to buy:");
		for(int i = 0; i < shoppingList.getLength(); i++) {
			System.out.println(shoppingList.elementAt(i));
		}
		System.out.println("Do we need to buy Bread? " + shoppingList.contains("Bread"));
	}
}