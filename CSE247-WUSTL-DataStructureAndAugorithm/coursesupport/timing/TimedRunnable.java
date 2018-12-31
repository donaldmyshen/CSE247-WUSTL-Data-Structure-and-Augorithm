package timing;

import java.time.Duration;
import java.time.Instant;

import timing.utils.Wrappers;

/**
 * Runs a specified Runnable, keeping track of
 *   when it started and ended,
 *   so that the Duration of the Runnable can be returned
 *   
 * @author roncytron
 *
 */
public class TimedRunnable extends Thread implements Runnable {

	final private RepeatRunnable runnable;
	private Instant start = null;
	private Instant end   = null;
	private Ticker ticker = null;
	private static boolean logging = true;

	/**
	 * Construct but do not yet run the Runnable
	 * @param r The Runnable that will be run
	 */
	public TimedRunnable(RepeatRunnable r) {
		this.runnable = r;
	}

	/**
	 * 1) Mark time (variable start), 
	 * 2) Run the Runnable, 
	 * 3) Mark time (variable end)
	 */
	@Override
	public void run() {
		if (this.end != null) {
			throw new Error("You can only run " + runnable + " once");
		}
		if (logging)
			System.out.println("Starting " + runnable + "...");
		//
		// Have the ResetRunnable set itself up again
		//   before we count timing
		//
		this.ticker = new Ticker();
		runnable.reset(this.ticker);
		//
		// collect your garbage, Java!
		// 
		System.gc();
		System.runFinalization();
		//
		//  and finish all the dead objects -- get all done you need to do
		//
		this.start = Instant.now();        // (1)
		synchronized (this) {
			try {
				runnable.run();                // (2)
			}
			catch (Throwable t) {
				this.end = Instant.now();      // (3)
				System.out.println("Runnable " + runnable + " aborted, threw error " + t);
				throw t;
			}
			finally {
				if (this.end == null)
					this.end = Instant.now();  // (3)
				if (logging)
					System.out.println("...Ending " + runnable );
				Wrappers.notifyAll(this); //Notifies everyone that this.end might have changed
			}
		}
	}

	/**
	 * If the Runnable as not yet completed execution, wait
	 * 
	 * @return the Duration of time taken by the Runnable
	 */
	public synchronized Duration getTime() {

		while(this.end == null){
			Wrappers.wait(this);	  //waits for the Runnable to finish
		}

		return Duration.between(start, end);
	}
	
	public Ticker getTicker() {
		return this.ticker;
	}

}
