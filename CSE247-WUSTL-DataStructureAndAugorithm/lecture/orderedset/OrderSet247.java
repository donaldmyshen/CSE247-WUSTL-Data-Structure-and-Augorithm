package orderedset;

import java.util.Random;

public class OrderSet247<T extends Comparable<T>> {
	
	private TreeNode<T> root;
	
	public OrderSet247() {
		this.root = null;
	}
	
	/**
	 * the public version of adding something to the tree, but..
	 * @param value
	 * @return
	 */
	public boolean add(T value) {
		if (contains(value))
			return false;
		
		// do the work of putting value into the tree
		this.root = addHelper(this.root, value);
		return true;
	}
	
	/**
	 * This helper allows us also to include where we are currently "at"
	 *   in the tree
	 * @param at
	 * @param value
	 * @return
	 */	
	
	private TreeNode<T> addHelper(TreeNode<T> at, T value) {
		//
		// if I get a null coming in, that means the left or right
		//    child (or root) was null, and so the correct thing
		//    to return is the value, created via a new TreeNode
		//  In this way, a null coming in is replaced by the new
		//    TreeNode with the specified value
		if (at == null) {
			return new TreeNode<T>(value);
		}
		// OK if we get here, we have to navigate right or left
		//  and the value here, at "at" tells us which way to go
		
		if (value.compareTo(at.value) < 0) { // value compared against the node's value
			// go left
			//  the helper will return the tree
			//  this node has always wanted as its
			//  left child.  It's usually the same
			//  tree that is passed in, but the helper
			//  can force this node to adopt a different
			//  tree (because of add) as its left child
			//
			at.left = addHelper(at.left, value);
		}
		else {
			at.right = addHelper(at.right, value);
		}
		return at;
	}
	
	/**
	 * We want the tree to be rotated 90 degrees counter clockwise
	 * So, we'll compute a String with 
	 * 		everything bigger than at, 
	 *      then at, then
	 *        everything smaller
	 */
	private String treeDumpHelper(TreeNode<T> at, int indent) {
		if (at == null)
			return "";
		String bigger = treeDumpHelper(at.right, indent+1); // compute the String of bigger stuff
		String smaller = treeDumpHelper(at.left, indent+1); // everything smaller
		String indentStr = computeIndent(indent);
		return bigger + "\n" + indentStr + at.value + "\n" + smaller;
	}
	
	private static String computeIndent(int indent) {
		if (indent == 0) 
			return "";
		else return "   " + computeIndent(indent-1);
	}
	
	private String treeDump() {
		return treeDumpHelper(this.root, 0);
		
	}


	private boolean contains(T value) {
		// for now assume the value isn't there
		return false;
	}
	
	public T first() {
		if (this.root == null) {
			throw new IllegalArgumentException("Tree is empty, no first");
		}
		TreeNode<T> at = this.root;
		while (at.left != null) {
			at = at.left;   // creep left
		}
		// here at.left is null, we have the smallest value
		return at.value;
	}
	public T last() {
		if (this.root == null) {
			throw new IllegalArgumentException("Tree is empty, no last");
		}
		TreeNode<T> at = this.root;
		while (at.right != null) {
			at = at.right;   // creep right
		}
		// here at.right is null, we have the largest value
		return at.value;
	}
	
	public static void main(String[] args) {
		OrderSet247<Integer> myset = new OrderSet247<Integer>();
		
		Random r = new Random(131);
		
		for (int i=0; i<5; ++i) {
			Integer num = r.nextInt(1000);
			System.out.println("have " +  num);
			myset.add(num);
		}
		System.out.println(myset.treeDump());
		System.out.println("smallest value is " + myset.first());
		System.out.println("largest value is " + myset.last());
	}


}
