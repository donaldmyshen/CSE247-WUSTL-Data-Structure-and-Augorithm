package heaps.validate;

public class HeapValidationError extends Error {
	
	final private String text;
	
	public HeapValidationError(String text) {
		this.text = text;
	}
	
	public String toString() {
		return "Heap Validation Error (detail shown below):\n" + text;
	}

}
