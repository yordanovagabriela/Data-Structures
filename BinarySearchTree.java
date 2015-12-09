public class BinarySearchTree<T extends Comparable<T>> {

	private static class BinaryTreeNode<T extends Comparable<T>> implements Comparable<BinaryTreeNode<T>> {
		//implementation from binarytree
	}

	private BinaryTreeNode<T> root;

	public BinarySearchTree() {
		this.root = null;
	}

	public void insert(T value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		this.root = insert(value, null, root);
	}

	private BinaryTreeNode<T> insert(T value, BinaryTreeNode<T> parentNode, BinaryTreeNode<T> node) {
		if(node == null) {
			node = new BinaryTreeNode<T>(value);
			node.parent = parentNode;
		} else {
			int compareTo = value.compareTo(node.value);
			if(compareTo < 0) {
				node.leftChild = insert(value, node, leftChild);
			} else if (compareTo> 0) {
				node.rightChild = insert(value, node, node.rightChild);
			}
		}
		return node;
	}

	private BinaryTreeNode<T> find(T value) {
		BinaryTreeNode<T> node = this.root;

		while(node != null) {
			int compareTo = value.compareTo(node.value);
			if(compareTo < 0) {
				node = node.leftChild;
			} else if (compareTo > 0) {
				node = node.rightChild;
			} else {
				break;
			}
		}
		return node;
	}

	public void remove(T value) {
		BinaryTreeNode<T> nodeToDelete = find(value);
		if(nodeToDelete == null) {
			return;
		}
		remove(nodeToDelete);
	}

	private void remove(BinaryTreeNode<T> nodeToDelete) {
		//If the node has two children.
		if(nodeToDelete.leftChild != null && nodeToDelete.rightChild != null) {
			BinaryTreeNode<T> replacement = nodeToDelete.rightChild;
			while(replacement.leftChild != null) {
				replacement = replacement.leftChild;
			}
			nodeToDelete.value = replacement.value;
			nodeToDelete = replacement;
		}
		//If the node has at most one child.
		BinaryTreeNode<T> theChild = nodeToDelete.leftChild != null ? nodeToDelete.leftChild : nodeToDelete.rightChild;
		//If the element to be deleted has one child.
		if(theChild != null) {
			theChild.parent = nodeToDelete.parent;
			//Handle the case when the element is the root.
			if(nodeToDelete.parent == null) {
				root = theChild;
			} else {
				//Replace the element with its child subtree
				if(nodeToDelete.parent.leftChild == nodeToDelete) {
					nodeToDelete.parent.leftChild = theChild;
				} else {
					nodeToDelete.parent.rightChild = theChild;
				}
			}
	} else {
		//Handle the case when the element is the root.
		if(nodeToDelete.parent == null) {
			root = null;
		} else {
			//Remove the element.It is a leaf.
			if(nodeToDelete.parent.leftChild == nodeToDelete) {
				nodeToDelete.parent.leftChild = null;
			} else {
				nodeToDelete.parent.rightChild = null;
			}
		}
	}
}