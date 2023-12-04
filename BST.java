package Lab10_Tree;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class BST<E extends Comparable<E>> {
	private BNode<E> root;

	public BST() {
		this.root = null;
	}

	public BNode<E> getRoot() {
		return root;
	}

	public void setRoot(BNode<E> root) {
		this.root = root;
	}

	// Add element e into BST
	public void add(E e) {
		if (isEmpty())
			root = new BNode<>(e);
		else
			this.root.add_Rescursive(root, e);
	}

	private boolean isEmpty() {
		return root == null;
	}

	// Add a collection of elements col into BST
	public void add(Collection<E> col) {
		for (E e : col)
			add(e);
	}

	// compute the depth of a node in BST
	public int depth(E node) {
		BNode<E> currentNode = this.root.contains(this.root, node);
		if (currentNode != null) {
			return currentNode.depth(currentNode);
		} else
			return -1;
	}

	// compute the height of BST
	public int height() {
		return this.root.depth(root);
	}

	// Compute total nodes in BST
	public int size() {
		return this.root.size(root);
	}

	// Check whether element e is in BST
	public boolean contains(E e) {
		return this.root.contains(root, e) != null;
	}

	// Find the minimum element in BST
	public E findMin() {
		return this.root.findMin(root);
	}

	// Find the maximum element in BST
	public E findMax() {
		return this.root.findMax(root);
	}

	// Remove element e from BST
	public boolean remove(E e) {
		if (this.root.getData().compareTo(e) == 0) {
			this.setRoot(this.root.removeRoot(e));
			return true;
		} else
			return this.root.remove(e);
	}

	// get the descendants of a node
	public List<E> descendants(E data) {
		BNode<E> currentNode = this.root.contains(this.root, data);
		if (currentNode == null)
			return null;
		else
			return currentNode.addDescendants(currentNode, currentNode.getData());
	}

	// get the ancestors of a node
	public List<E> ancestors(E data) {
		if (this.root.contains(root, data) == null)
			return null;
		else
			return this.root.addAncestors(data);
	}

	// Task 2
	// display BST using inorder approach
	public void inorder() {
		this.root.inorder(this.root);
	}

	// display BST using preorder approach
	public void preorder() {
		this.root.preorder(this.root);
	}

	// display BST using postorder approach
	public void postorder() {
		this.root.postorder(this.root);
	}

	@Override
	public String toString() {
		return "" + root;
	}

	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		bst.add(25);
		bst.add(15);
		bst.add(10);
		bst.add(44);
		bst.add(12);
		bst.add(18);
		bst.add(22);
		bst.add(35);
		bst.add(24);
		bst.add(31);
		bst.add(4);
		bst.add(50);
		bst.add(70);
		bst.add(66);
		bst.add(90);
//		  bst.inorder();
//		  bst.preorder();
//        bst.postorder();
//        System.out.println(bst.descendants(50));
//        System.out.println(bst.ancestors(18));
//        System.out.println(bst.contains(66));
//        System.out.println(bst.contains(37));
//        System.out.println(bst.depth(25));
//        System.out.println(bst.height());
//        System.out.println(bst.size());
//        System.out.println(bst.remove(25));
//        System.out.println(bst.remove(50));
//        System.out.println(bst.remove(60));
//        System.out.println(bst.findMin());
//        System.out.println(bst.findMax());
	}
}