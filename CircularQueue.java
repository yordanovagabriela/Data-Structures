import java.util.*;

public class CircularQueue<T> {

	private static final int DEFAULT_CAPACITY = 10;
	private int front, rear, count;
	private ArrayList<T> queue;

	public CircularQueue() {
		front = 0;
		rear = 0;
		count = 0;
		queue = new ArrayList<T>(DEFAULT_CAPACITY);
	}

	public CircularQueue(int initialCapacity) {
		front = 0;
		rear = 0;
		count = 0;
		queue = new ArrayList<T>(initialCapacity);
	}

	public int size() {
		return count;
	}

	public  boolean isEmpty() {
		return (count == 0);
	}

	public void enqueue(T element) {
		if(size() == queue.size()) {
			throw new RuntimeException("The queue is full.");
		}
		queue.add(rear++, element);
		count++;
	}

	public T dequeue() {
		if(isEmpty()) {
			throw new RuntimeException("The queue is empty!");
		}
		T result = queue.get(front);
		queue.set(front++, null);
		count--;

		return result;
	}

	public T first() {
		if(isEmpty()) {
			throw new RuntimeException("The queue is empty!");
		}
		return queue.get(front);
	}

	public static void main(String[] args) {
		CircularQueue<String> queue = new CircularQueue<String>();
	}
}