package timing;

/**
 * A Runnable whose execution can be repeated, for timing purposes,
 * because a given run may have interference from other activity on
 * a computer.
 * @author roncytron
 *
 */
public interface RepeatRunnable {
	
	/**
	 * 	 Called before each execution of run.  This method is used
	 *   to reset an implementation to the point where it should
	 *   be timed.
	 * @param ticker The Ticker to be used for this run
	 */
	
	public void reset(Ticker ticker);
	/**
	 * The actual workload to be performed after reset().
	 */
	public void run();

}
