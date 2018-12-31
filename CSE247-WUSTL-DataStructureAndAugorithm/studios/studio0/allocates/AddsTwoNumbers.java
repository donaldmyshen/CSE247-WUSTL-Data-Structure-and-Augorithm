package studio0.allocates;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;


public class AddsTwoNumbers extends QuietAlgorithm {

	protected Ticker ticker;
	protected long   sum;

	public AddsTwoNumbers() {

	}

	/**
	 * Begin with an array of two elements.
	 */
	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
	}

	/**
	 * As a "quiet" algorithm, all we care about is the
	 * parameter n.
	 */
	@Override
	public void run() {
		if (this.n == 0)  // special case to get 0 time
			return;
		//
		// We loop so that the allocation takes place many times,
		//   so that its time shows up in the milliseconds range
		// If your run is taking too long, decrease the upper bound
		//   of the loop.
		// If your data is showing 0 time (not ticks, they should be OK), increase the upper bound
		//   of the loop
		//
		for (long i=0; i < 100000000L; ++i) {
			//
			// Add two numbers
			//
			this.sum = this.n + i;
			//
			// Account for one operation, the time it takes to sum two values above
			//
			ticker.tick();
		}
	}
	
	public String toString() {
		return "Summing two integers";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(0, 10000, 500);
		ExecuteAlgorithm.timeAlgorithm(
				"addstwonumbers", 
				"studio0.allocates.AddsTwoNumbers", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
