import java.util.*;

public class CustomArrayList {
	private Object[] arr;
	private int count;
	private static final int INITIAL_CAPACITY = 4;

	public CustomArrayList() {
		arr = new Object[INITIAL_CAPACITY];
		count = 0;
	}

	public int getLength() {
		return count;
	}

	public void add(Object item) {
		add(count, item);
	}

	public void add(int index, Object item) {
		if(index > count || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		Object[] extendedArr = arr;
		if(count + 1 == arr.length) {
			extendedArr = new Object[arr.length * 2];
		}

		System.arraycopy(arr, 0, extendedArr, 0, index);
		count++;
		System.arraycopy(arr, index, extendedArr, index + 1, count - index - 1);
		extendedArr[index] = item;
		arr = extendedArr;
	}

	public int indexOf(Object item) {
		if(item == null) {
			for(int i = 0; i < arr.length; i++) {
				if(arr[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < arr.length; i++) {
				if(item.equals(arr[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	public void clear() {
		arr = new Object[0];
		count = 0;
	}

	public boolean contains(Object item) {
		int index = indexOf(item);
		boolean found= (index != -1);
		return found;
	}

	public Object elementAt(int index) {
		if(index >= count || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		return arr[index];
	}

	public Object remove(int index) {
		if(index >= count || index < 0) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		Object item = arr[index];
		System.arraycopy(arr, index + 1, arr, index, count - index + 1);
		arr[count - 1] = null;
		count--;
		return item;
	}

	public int remove(Object item) {
		int index = indexOf(item);
		if(index == -1) {
			return index;
		}
		System.arraycopy(arr, index + 1, arr, index, count - index + 1);
		count--;
		return index;
	}

	public static void main(String[] args) {
		CustomArrayList shoppingList = new CustomArrayList();
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

//  0   1   2   3   4   5
// |1| |2| |3| |4| | | | |

// index = 2;
// count = 5;
// 			  0 1  2  3 4  5
// extendedArr = 1 2 | | 3 4 | |