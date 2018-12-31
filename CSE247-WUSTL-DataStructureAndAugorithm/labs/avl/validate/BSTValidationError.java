package avl.validate;

public class BSTValidationError extends Error {
	
	final private String text;
	
	public BSTValidationError(String text) {
		this.text = text;
	}
	
	public String toString() {
		return "Tree Validation Error (detail shown below):\n" + text;
	}

}
