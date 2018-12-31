package orderedset;

public class TreeNode<T extends Comparable<T>> {
	
	//  omit private or public below so the fields are accessible
	//  by other code is package
	
	T value;
	TreeNode<T> left, right;
	
	public TreeNode(T value) {
		this.value = value;
		this.left  = null;
		this.right = null;
	}
	
	public String toString() {
		return this.value + "";
	}

}
