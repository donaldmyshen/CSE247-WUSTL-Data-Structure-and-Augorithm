package avl;

//
// class TreeNode
// Implements a node of binary tree
//
public class TreeNode<T extends Comparable<T>> {

    public T value;            // key associated with node

    public TreeNode<T> left;   // left child
    public TreeNode<T> right;  // right child
    public TreeNode<T> parent; // parent ptr

    // height of subtree rooted at this node -- you need to
    // set and maintain this quantity yourself in AVLTree.java!
    public int height;
    
    // constructor
    public TreeNode(T value) {
        this.value = value;
	
        this.left = null;
        this.right = null;
        this.parent = null;
    }
    
    // Attach node "left" as the left subtree of this node.
    // Sets child and, if appropriate, parent pointer
    public void setLeft(TreeNode<T> left) {
        this.left = left;
	if (left != null)
	    left.parent = this;
    }

    // Attach node "right" as the right subtree of this node.
    // Sets child and, if appropriate, parent pointer
    public void setRight(TreeNode<T> right) {
        this.right = right;
	if (right != null)
	    right.parent = this;
    }
    
    public String toString() {
        return this.value.toString();
    }
}
