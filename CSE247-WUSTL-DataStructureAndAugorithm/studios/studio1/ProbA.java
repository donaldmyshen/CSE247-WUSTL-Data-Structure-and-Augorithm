package studio1;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;


public class ProbA extends QuietAlgorithm {

	protected Ticker ticker;
	protected long   sum;

	public ProbA() {

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
		
		for (int i=1; i<n; i++) {
			int array[]=new int[i];
			ticker.tick();
		}
	}
	
	public String toString() {
		return "Problem A";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(1,11, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"ProbA", 
				"studio1.ProbA", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
