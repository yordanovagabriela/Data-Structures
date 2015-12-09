public class BinaryTree<T> {

	public static class BinaryTreeNode<T> {
		private T value;
		private boolean hasParent;
		private BinaryTreeNode<T> leftChild;
		private BinaryTreeNode<T> rightChild;

		public BinaryTreeNode(T value, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild) {
			if(value == null) {
				throw new IllegalArgumentException("Cannot insert null value!");
			}

			this.value = value;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		public BinaryTreeNode(T value) {
			this(value, null, null);
		}

		public T getValue() {
			return this.value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public BinaryTreeNode<T> getLeftChild() {
			return this.leftChild;
		}

		public void setLeftChild(BinaryTreeNode<T> child) {
			if(child == null || child.hasParent) {
				throw new IllegalArgumentException();
			}

			child.hasParent = true;
			this.leftChild = child;
		}

		public BinaryTreeNode<T> getRightChild() {
			return this.rightChild;
		}

		public void setRightChild(BinaryTreeNode<T> child) {
			if(child == null || child.hasParent) {
				throw new IllegalArgumentException();
			}

			child.hasParent = true;
			this.rightChild = child;
		} 

	}

	private BinaryTreeNode<T> root;

	public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
		if(value == null) {
			throw new IllegalArgumentException();
		}

		BinaryTreeNode<T> leftChildNode = leftChild != null ? leftChild.root : null;
		BinaryTreeNode<T> rightChildNode = rightChild != null ? rightChild.root : null;
		this.root = new BinaryTreeNode<T>(value, leftChildNode, rightChildNode);
	}

	public BinaryTree(T value) {
		this(value, null, null);
	}

	public BinaryTreeNode<T> getRoot() {
		return this.root;
	}

	public BinaryTreeNode<T> getLeftChildNode() {
		if(this.root != null) {
			return this.root.getLeftChild();
		}
		return null;
	}

	public BinaryTreeNode<T> getRightChildNode() {
		if(this.root != null) {
			return this.root.getRightChild();
		}
		return null;
	}

	private void printPreOrder(BinaryTreeNode<T> root) {
		if(root == null) {
			return;
		}

		printPreOrder(root.getLeftChild());
		System.out.print(root.getValue() + " ");
		printPreOrder(root.getRightChild());
	}

	public void printPreOrder() {
		printPreOrder(this.root);
		System.out.println();
	}
}

public class BinaryTreeExample {
	public static void main(String[] args) {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>(14,
			new BinaryTree<Integer>(19,
				new BinaryTree<Integer>(23),
				new BinaryTree<Integer>(6,
					new BinaryTree<Integer>(10),
					new BinaryTree<Integer>(21))),
			new BinaryTree<Integer>(15,
				new BinaryTree<Integer>(3),
				null));

		BinaryTree.printPreOrder();
	}
}

//TO DO BFS AND DFS !!!!!!!!!!!

// 			14

// 	  19			15

// 23     6       3 	null

//     10   21
