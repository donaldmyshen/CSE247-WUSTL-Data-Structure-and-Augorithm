package studio0.allocates;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;


public class Allocates extends QuietAlgorithm {

	protected int[] array;
	protected Ticker ticker;

	public Allocates() {

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
		//
		// We loop so that the allocation takes place many times,
		//   so that its time shows up in the milliseconds range
		//
		for (int i=0; i < 100000; ++i) {
			//
			// Allocate this.size integers
			// Does the statement below take constant time?
			//     Or does it vary with this.size?
			//     If it does vary, how does it vary?
			//
			this.array = new int[this.n];
			//
			// Is the following ticker statement
			//   an accurate count of the operations
			//   taken by the above statement? 
			//   Does it take one tick to allocate n integers?
			//
			ticker.tick();
		}
	}
	
	public String toString() {
		return "Allocation of " + n + " integers";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(0, 10000, 500);
		ExecuteAlgorithm.timeAlgorithm(
				"allocates", 
				"studio0.allocates.Allocates", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
