import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Tree<T> {

	public static class TreeNode<T> {
		private T value;
		private boolean hasParent;
		private ArrayList<TreeNode<T>> children;

		//Constructs a tree node.
		public TreeNode(T value) {
			if(value == null) {
				throw new IllegalArgumentException("Cannot insert null value!");
			}
			this.value = value;
			this.children = new ArrayList<TreeNode<T>>();
		}

		public T getValue() {
			return this.value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public void addChild(TreeNode<T> child) {
			if(child == null) {
				throw new IllegalArgumentException("Cannot insert null value!");
			}
			if(child.hasParent) {
				throw new IllegalArgumentException("The node already has a parent!");
			}
			child.hasParent = true;
			this.children.add(child);
		}

		public TreeNode<T> getChild(int index) {
			return this.children.get(index);
		}

		public int getChildernCount() {
			return this.children.size();
		}
	}
	//Constructs the tree class.
	private TreeNode<T> root;

	private Queue<TreeNode<T>> queueBFS = new LinkedList<TreeNode<T>>();

	public Tree(T value) {
		if(value == null) {
			throw new IllegalArgumentException("Cannot insert null value!");
		}
		this.root = new TreeNode<T>(value);
	}

	public Tree(T value, Tree<T> ...children) {
		this(value);
		for(Tree<T> child : children) {
			this.root.addChild(child.root);
		}
	}

	public TreeNode<T> getRoot() {
		return this.root;
	}

	public ArrayList<TreeNode<T>> getChildNodes() {
		if(this.root != null) {
			return this.root.children;
		}
		return new ArrayList<TreeNode<T>>();
	}

	private void printDFS(TreeNode<T> root, String spaces) {
		if(this.root == null) {
			return;
		}
		System.out.println(spaces + root.getValue());

		TreeNode<T> child = null;
		for(int i = 0; i < root.getChildernCount(); i++) {
			child = root.getChild(i);
			printDFS(child, spaces + "   ");
		}
	}

	public void printDFS() {
		this.printDFS(this.root, new String());
	}
	//needs changes!!
	public void dfs(TreeNode<T> root) {
		if(root == null) {
			return;
		}
		TreeNode<T> child = null;
		for(int i = 0; i < root.getChildernCount(); i++) {
			child = root.getChild(i);
			dfs(child);
		}
		System.out.print(root.getValue() + " -> ");
	}

	//without the root!!
	public void bfs(TreeNode<T> root) {
		queueBFS.add(root);

		while(!queueBFS.isEmpty()) {
			TreeNode<T> temp = queueBFS.remove();
			System.out.print(temp.getValue() + " -> ");
			for(int i = 0; i < temp.getChildernCount(); i++) {
				queueBFS.add(temp.getChild(i));
				//System.out.print(temp.getChild(i).getValue() + " -> ");
			}
		}
	}


}

public class TreeExample {
	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<Integer>(7,
			new Tree<Integer>(19,
				new Tree<Integer>(1),
				new Tree<Integer>(12),
				new Tree<Integer>(31)),
			new Tree<Integer>(21),
			new Tree<Integer>(14,
				new Tree<Integer>(23),
				new Tree<Integer>(6))
			);
		System.out.println("The root is: " + tree.getRoot().getValue());
		tree.printDFS();
		tree.dfs(tree.getRoot());
		System.out.println();
		tree.bfs(tree.getRoot());
	}

}
