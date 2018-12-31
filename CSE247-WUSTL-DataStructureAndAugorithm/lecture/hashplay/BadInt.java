package hashplay;

public class BadInt {
	
	private int value;

	public BadInt(int value) {
		super();
		this.value = value;
	}
	
	public int hashCode() {
		return value;
	}
	
	public boolean equals(Object o) {
		BadInt other = (BadInt) o;
		return this.value == other.value;
	}

	@Override
	public String toString() {
		return "BadInt [value=" + value + "]";
	}
	
	

}
