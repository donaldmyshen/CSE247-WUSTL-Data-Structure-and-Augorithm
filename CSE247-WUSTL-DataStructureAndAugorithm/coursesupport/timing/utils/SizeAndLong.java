package timing.utils;

/**
 * Simple aggregating class.
 * @author roncytron
 *
 */
public class SizeAndLong {
	
	public final int  size;
	public final long value;
	
	public SizeAndLong(int size, long value) {
		this.size = size;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "SizeAndTicks [size=" + size + ", value=" + value + "]";
	}

}
