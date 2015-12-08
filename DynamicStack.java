import java.util.*;

public class DynamicStack {

	private Object[] stack;
	private static final int INITIAL_CAPACITY = 4;
	private int size;
	private int top;
	private int capacity;

	public DynamicStack() {
		stack = new Object[INITIAL_CAPACITY];
		size = 0;
		top = -1;
		capacity = INITIAL_CAPACITY;
	}

	public int capacity() {
		return capacity;
	}

	public int size() {
		return size;
	}

	public boolean empty() {
		return (size() == 0);
	}

	public void push(Object item) {
		if(capacity == (size + 1)) {
			Object[] temp = new Object[capacity* 2];
			System.arraycopy(stack, 0, temp, 0, size);
			capacity *= 2;
			//temp[++top] = item;
			stack = temp;
			//size++;
		} 
		stack[++top] = item;
		size++;
	}

	public Object peek() {
		if(empty()) {
			throw new RuntimeException("The stack is empty.");
		}
		return stack[top];
	}

	public Object pop() {
		if(empty()) {
			throw new RuntimeException("The stack is empty.");
		}		
		size--;
		return stack[top--];
	}

	public int search(Object item) {
		Object[] temp = new Object[size];
		System.arraycopy(stack, 0, temp, 0, size);
		int tempTop = top;
		int index = size - tempTop;
		while(tempTop >= 0) {
			if(temp[tempTop] == item) {
				return index;
			} else {
				tempTop--;
				index++;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		DynamicStack stack = new DynamicStack();
		for(int i = 1; i <= 100; i++) {
			stack.push(i);
		}
		System.out.println(stack.search(100000));
		System.out.println("Is the stack empty? " + stack.empty());
		System.out.println("What is the size of the stack? " + stack.size());
		System.out.println("What is the capacity of the stack? " + stack.capacity());
		System.out.println("What is the last element in the stack? " + stack.peek());

		// stack.pop();

		// System.out.println("Is the stack empty? " + stack.empty());
		// System.out.println("What is the size of the stack? " + stack.size());
		// System.out.println("What is the capacity of the stack? " + stack.capacity());
		// System.out.println("What is the last element in the stack? " + stack.peek());
	}
}