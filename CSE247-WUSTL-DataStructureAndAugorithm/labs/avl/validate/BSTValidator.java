package avl.validate;

import avl.TreeNode;
import avl.AVLTree;
import avl.util.TreeToStrings;

public class BSTValidator<T extends Comparable<T>> {
    final public AVLTree<T> tree;
    private String before;

    public BSTValidator(final AVLTree<T> tree) {
        this.tree = tree;
        this.before = tree.toString();
    }

    /**
     * The instance variable "before" captures the state of the heap
     * last time we looked.   This method runs our validation methods,
     * and if something goes wrong, it reports the before tree, the error,
     * and the after tree.  This should provide enough information to
     * diagnose your problems with your binary heap implementation.
     */
    public void check() {
        try {
            TreeNode<T> root = tree.getRoot();
            CheckTree(root, null);
	    
            before = TreeToStrings.toTree(tree);
        } catch (Throwable t) {
            String oops = "\nTree before the problem occurred:\n";
	    if (tree.size > 100)
		oops += " (not printing large tree, n=" + tree.size + ")\n";
	    else
		oops += before + "\n";
            
            oops += "What went wrong: " + t.getMessage() + "\n";
	    oops += "Tree that triggered this problem:" + "\n";
	    if (tree.size > 100)
		oops += " (not printing large tree, n=" + tree.size + ")\n";
	    else
		oops += TreeToStrings.toTree(tree);

            t.printStackTrace();
            throw new BSTValidationError(t + "" + oops);
        }
    }
    
    /**
     * Check that the tree is a valid AVL tree.
     * In particular, make sure that
     *    - it satsfies the (strict) BST property 
     */
    public void CheckTree(TreeNode<T> child, TreeNode<T> parent) {
        if (child == null) {
            return;
        } else {
	        CheckTree(child.left, child);
            CheckTree(child.right, child);
	    
            if (parent != null)
            {
            	if (child == parent.left && child.value.compareTo(parent.value) >= 0) {
            		throw new BSTValidationError(String.format("The left child {%s} is >= its parent {%s} ", 
            				child.value, parent.value));
            	}
            	
		    if (child == parent.right && child.value.compareTo(parent.value) <= 0) {
		    	throw new BSTValidationError(String.format("The right child {%s} is <= its parent {%s} ", 
		    			child.value, parent.value));
		    	}
		    
		    if (child != parent.left && child != parent.right) {
		    	throw new BSTValidationError(String.format("Parent {%s} and child {%s} are not properly linked.", 
		    			parent.value, child.value));
		    	}
            }
            
		    if (!validateHeight(child)) {
		    	throw new BSTValidationError(String.format("The node {%s} has height %d, which is not the expected height given its children",
		    		child.value, child.height));
		    }
	    
		    if (!validateBalance(child)) {
		    	throw new BSTValidationError(String.format("The tree is not balanced starting at node %s", child.value));
		    }
        }
    }
        
    private boolean validateHeight(TreeNode<T> node) {
    	if (node.left == null && node.right == null)
    		return true;
    	else if (node.left == null)
    		return (node.height == node.right.height + 1);
    	else if (node.right == null)
    		return (node.height == node.left.height + 1);
    	else
    		return (node.height == Math.max(node.left.height,
    										node.right.height) + 1);
    }
    
    private boolean validateBalance(TreeNode<T> node) {
    	if (node.left == null && node.right == null)
    		return true;
    	else if (node.left == null)
    		return (node.right.left == null && node.right.right == null);
    	else if (node.right == null)
    		return (node.left.left == null && node.left.right == null);
    	else
    		return (Math.abs(node.right.height - node.left.height) <=1);
    }
}