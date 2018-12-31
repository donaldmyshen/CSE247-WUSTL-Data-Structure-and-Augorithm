package studio1;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;


public class ProbB extends QuietAlgorithm {

	protected Ticker ticker;
	protected long   sum;

	public ProbB() {

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
		int j = 1;
		while (j < n) {
			int k = n;
			while (k > j) {
				//
				// Here
				ticker.tick();
				//				
				k = k - 1;
			}
			j = j + 1;
		}
	}
	
	public String toString() {
		return "Problem B";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(1, 100, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"ProbB", 
				"studio1.ProbB", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
