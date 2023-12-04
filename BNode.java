package Lab10_Tree;

import java.util.LinkedList;
import java.util.List;

public class BNode<E extends Comparable<E>> {
	private E data;
	private BNode<E> left;
	private BNode<E> right;

	public BNode(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public BNode(E data, BNode<E> left, BNode<E> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public E getData() {
		return data;
	}

	public BNode<E> getLeft() {
		return left;
	}

	public BNode<E> getRight() {
		return right;
	}

	public void setData(E data) {
		this.data = data;
	}

	public void setLeft(BNode<E> left) {
		this.left = left;
	}

	public void setRight(BNode<E> right) {
		this.right = right;
	}

	public void add_Rescursive(BNode<E> node, E e) {
		if (e.compareTo(node.getData()) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new BNode<>(e));
			} else {
				add_Rescursive(node.getLeft(), e);
			}
		}
		if (e.compareTo(node.getData()) > 0) {
			if (node.getRight() == null) {
				node.setRight(new BNode<>(e));
			} else {
				add_Rescursive(node.getRight(), e);
			}
		}
	}

	public int depth(BNode<E> node) {
		// neu node co 2 con
		if (node.getLeft() != null && node.getRight() != null) {
			return Math.max(1 + depth(node.getLeft()), 1 + depth(node.getRight()));
		}
		// neu node khong co con
		else if (node.getLeft() == null && node.getRight() == null)
			return 0;
		// neu node co 1 con
		else if (node.getLeft() == null) {
			return 1 + depth(node.getRight());
		} else {
			return 1 + depth(node.getLeft());
		}
	}

	public BNode<E> contains(BNode<E> root, E node) {
		if (root != null) {
			if (root.getData().compareTo(node) > 0)
				return contains(root.getLeft(), node);
			else if (root.getData().compareTo(node) < 0)
				return contains(root.getRight(), node);
			else
				return root;
		} else
			return null;
	}

	public int size(BNode<E> node) {
		if (node == null)
			return 0;
		else {
			return size(node.getLeft()) + 1 + size(node.getRight());
		}
	}

	public E findMin(BNode<E> e) {
		if (e.getLeft() == null)
			return e.getData();
		else
			return findMin(e.getLeft());
	}

	public E findMax(BNode<E> e) {
		if (e.getRight() == null)
			return e.getData();
		else
			return findMax(e.getRight());
	}

	public boolean remove(E e) {
		BNode<E> removeNode = contains(this, e);
		if (removeNode == null)
			return false;
		else {
			BNode<E> root = findNodeParent(this, e);
			// if Node don't have child
			if (removeNode.getLeft() == null && removeNode.getRight() == null) {
				if (root.getData().compareTo(removeNode.getData()) < 0)
					root.setRight(null);
				else
					root.setLeft(null);
			}
			// if Node have 1 child
			else if (removeNode.getLeft() == null)
				root.setRight(removeNode.getRight());
			else if (removeNode.getRight() == null)
				root.setLeft(removeNode.getLeft());
			// if Node have 2 child
			else {
				BNode<E> newNode = findSuccessor(removeNode.getRight());
				// recreate Successor node
				remove(newNode.getData());
				newNode.setLeft(removeNode.getLeft());
				newNode.setRight(removeNode.getRight());
				// add Successor node
				if (root.getData().compareTo(newNode.getData()) < 0)
					root.setRight(newNode);
				else
					root.setLeft(newNode);
			}
		}
		return true;
	}

	// remove root
	public BNode<E> removeRoot(E e) {
		BNode<E> removeNode = contains(this, e);
		BNode<E> newNode = findSuccessor(removeNode.getRight());
		// recreate Successor node
		remove(newNode.getData());
		newNode.setLeft(removeNode.getLeft());
		newNode.setRight(removeNode.getRight());
		// add Successor node
		return newNode;
	}

	public BNode<E> findSuccessor(BNode<E> node) {
		if (node.getLeft() == null)
			return node;
		else
			return findSuccessor(node.getLeft());
	}

	public BNode<E> findNodeParent(BNode<E> node, E data) {
		// if Node have 2 child
		if (node.getLeft() != null && node.getRight() != null) {
			if (node.getLeft().getData().compareTo(data) == 0 || node.getRight().getData().compareTo(data) == 0)
				return node;
			else {
				BNode<E> leftValue = findNodeParent(node.getLeft(), data);
				if(leftValue!=null)
					return leftValue;
				else return findNodeParent(node.getRight(), data);
			}
		}
		// if Node have 0 child
		else if (node.getLeft() == null && node.getRight() == null)
			return null;
		// if Node have 1 child
		else {
			if (node.getLeft() == null) {
				if (node.getRight().getData().compareTo(data) == 0)
					return node;
				else
					return findNodeParent(node.getRight(), data);
			} else if (node.getLeft().getData().compareTo(data) == 0)
				return node;
			else
				return findNodeParent(node.getLeft(), data);
		}
	}

	// for descendants method
	public List<E> addDescendants(BNode<E> node, E data) {
		List<E> result = new LinkedList<>();
		if (node != null) {
			if (node.getData().compareTo(data) != 0)
				result.add(node.getData());
			if (node.getLeft() != null) {
				result.addAll(addDescendants(node.left, data));
			}
			if (node.getRight() != null) {
				result.addAll(addDescendants(node.right, data));
			}
		}
		return result;
	}

	// for ancestor method
	public List<E> addAncestors(E data) {
		List<E> result = new LinkedList<>();
		int compareValue = this.getData().compareTo(data);
		if (compareValue != 0)
			result.add(this.getData());
		if (compareValue > 0 && this.left != null) {
			result.addAll(this.left.addAncestors(data));
		} else {
			if (this.right != null) {
				result.addAll(this.right.addAncestors(data));
			}
		}
		return result;
	}

	// print BST
	public void inorder(BNode<E> node) {
		if (node.getLeft() != null)
			inorder(node.getLeft());
		System.out.print(node + " ");
		if (node.getRight() != null)
			inorder(node.getRight());
	}

	public void preorder(BNode<E> node) {
		System.out.print(node + " ");
		if (node.getLeft() != null)
			preorder(node.getLeft());
		if (node.getRight() != null)
			preorder(node.getRight());
	}

	public void postorder(BNode<E> node) {
		if (node.getLeft() != null)
			postorder(node.getLeft());
		if (node.getRight() != null)
			postorder(node.getRight());
		System.out.print(node + " ");
	}

	@Override
	public String toString() {
		return "" + data ;
	}

}
