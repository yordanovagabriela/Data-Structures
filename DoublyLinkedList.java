import java.util.*;

public class DoublyLinkedList {

	private class Node {
		Object element;
		Node previousNode;
		Node nextNode;

		Node(Object element) {
			this.element = element;
		}
	}

	private Node head;
	private Node tail;
	private int count;

	public DoublyLinkedList() {
		head = null;
		count = 0;
	}

	public void insertAtLastPosition(Object item) {
		Node newNode = new Node(item);
		if(head == null) {
			head = newNode;
		} else {
			tail.nextNode = newNode;
			newNode.previousNode = tail;
		}
		tail = newNode;
		count++;
	}

	public void insertAtFirstPosition(Object item) {
		Node newNode = new Node(item);
		if(head == null) {
			tail = newNode;
		} else {
			head.previousNode = newNode;
		}
		newNode.nextNode = head;
		head = newNode;
		count++;
	}

	public void insertAtInnerPosition(Object item, int index) {
		if(index >= count || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		if(index == 0) {
			insertAtFirstPosition(item);
			return;
		}
		if(index == count - 1) {
			insertAtLastPosition(item);
			return;
		}

		Node newNode = new Node(item);
		Node current = head;
		int ind = 0;

		while(current.nextNode != null && ind != index) {
			current = current.nextNode;
			ind++;
		}
		(current.previousNode).nextNode = newNode;
		newNode.previousNode = (current.previousNode);
		newNode.nextNode = current;
		current.previousNode = newNode;
		count++;
		
	}

	public void deleteFirst() {
		if(head.nextNode == null) {
			tail = null;
		} else {
			head.nextNode.previousNode = null;
		}
		head = head.nextNode;
		count--;
	}

	public void deleteLast() {
		if(head.nextNode == null) {
			head = null;
		} else {
			(tail.previousNode).nextNode = null;
		}
		tail = tail.previousNode;
		count--;
	}

	public void deleteIndex(int index) {
		if(index >= count || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		if(index == 0) {
			deleteFirst();
			return;
		}
		if(index == count - 1) {
			deleteLast();
			return;
		}

		Node current = head;
		int ind = 0;

		while(current.nextNode != null && ind != index) {
			ind++;
			current = current.nextNode;
		}

		(current.previousNode).nextNode = current.nextNode;
		(current.nextNode).previousNode = current.previousNode; 
		count--;
		
	}

	public int deleteItem(Object item) {
		if(!contains(item)) {
			return -1;
		}

		int index = 0;
		Node current = head;
		while(current != null) {
			if((current.element != null && current.element.equals(item)) || (current.element == null) && (item == null)) {
				break;
			}
			current = current.nextNode;
			index++;
		}
		deleteIndex(index);
		return index;
	}

	public boolean contains(Object item) {
		int index = indexOf(item);
		boolean found = (index != -1);
		return found;
	}

	public int indexOf(Object item) {
		int index = 0;
		Node current = head;
		while(current != null) {
			if((current.element != null && current.element.equals(item)) || (current.element == null) && (item == null)) {
				return index;
			}
			current = current.nextNode;
			index++;
		}
		return -1;		
	}

	public int getLength() {
		return count;
	}

	public Object elementAt(int index) {
		if(index >= count || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		Node currentNode = this.head;
		for(int i = 0; i < index; i++) {
			currentNode = currentNode.nextNode;
		}
		return currentNode.element;
	}

	public Object[] getArray() {
		Object[] objects = new Object[getLength()];
		for(int i = 0; i < getLength(); i++) {
			objects[i] = elementAt(i);
		}
		return objects;
	}

	public static void main(String[] args) {
		DoublyLinkedList dll = new DoublyLinkedList();
		dll.insertAtLastPosition(1);
		dll.insertAtLastPosition(2);
		dll.insertAtLastPosition(5);
		//dll.deleteIndex(2);
		//dll.insertAtInnerPosition(0, 2);
		//dll.deleteIndex(1);
		//dll.deleteFirst();
		//dll.deleteLast();
		//dll.deleteLast();
		for(Object o: dll.getArray()) {
			System.out.println(o);
		}
	}
}