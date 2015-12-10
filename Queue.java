import java.util.ArrayList;
import java.util.List;

public class Queue<T> {

	private Node<T> front;
	private Node<T> rear;
	private int size;
	
	private static class Node<T> {
		private T data;
		private Node<T> next;
	}
	
	public Queue() {
		front = null;
		rear = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public T peek() {
		return front.data;
	}
	
	public void enqueue(T element) {
		Node<T> newNode = new Node<>();
		newNode.data = element;
		newNode.next = null;
		if(front == null) {
			front = newNode;
			rear = front;
		} else {
			rear.next = newNode;
			rear = newNode;
		}
		size++;
	}
	
	public T dequeue() {
		if(this.isEmpty()) {
			return null;
		}else {
			T el = front.data;
			front = front.next;
			size--;
			return el;
		}
	}

    public String toString() {
        StringBuilder str = new StringBuilder();
        Node<T> current = front;
        while(current != null) {
        	str.append(current.data + " ");
            current = current.next;
        }
        return str.toString();
    } 
	
    public boolean insertAfter(Node<T> node, T data) {
    	//TODO also check if node is rear !!
    	if(node != null) {
        	Node<T> newNode = new Node<>();
        	newNode.data = data;
        	newNode.next = node.next;
        	node.next = newNode;  
        	return true;
    	} else {
    		return false;
    	}

    }
    
    public Node<T> search(T data) {
    	Node<T> current = front;
    	while(current != null) {
    		if(current.data == data) {
    			return current;
    		} else {
    			current = current.next;
    		}
    	}
    	return null;
    }
    
	public static void main(String[] args) {
		Queue<Integer> q = new Queue<>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(33);
		Node<Integer> n = q.search(2);
		q.insertAfter(n, 10);
		System.out.println(q);
	}
}
