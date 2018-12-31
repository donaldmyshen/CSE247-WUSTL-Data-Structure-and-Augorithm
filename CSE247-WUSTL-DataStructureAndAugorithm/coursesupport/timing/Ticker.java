package timing;

/**
 * Keeps track of the number of operations associated with an
 * implementation.
 * @author roncytron
 *
 */
final public class Ticker {
	
	private long tickCount;

	public Ticker() {
		this.tickCount = 0L;
	}
	
	/**
	 * Log one more operation on behalf of the implementation.
	 */
	final public void tick() {
		++tickCount;
	}
	
	final public void tick(long ticks) {
		tickCount += ticks;
	}
	
	final public long getTickCount() {
		return this.tickCount;
	}

}
