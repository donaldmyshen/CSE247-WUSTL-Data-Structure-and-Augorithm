package studio1;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;


public class ProbC extends QuietAlgorithm {

	protected Ticker ticker;
	protected long   sum;

	public ProbC() {

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
		int j = 0;
		while (j <= n) {
			int k = 0;
			while (k < j) {
				//
				// Here
				ticker.tick();
				//				
				k = k + 1;
			}
			j = j + 1;
		}
	}
	
	public String toString() {
		return "Problem C";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(1, 100, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"ProbC", 
				"studio1.ProbC", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
