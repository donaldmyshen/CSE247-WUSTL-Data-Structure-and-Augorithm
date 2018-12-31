package studio1;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;


public class FromLecture extends QuietAlgorithm {

	protected Ticker ticker;
	protected long   sum;

	public FromLecture() {

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
		while (j <= n/2) {
			for (int k=0; k < 3; ++k) {
				//
				// Here
				ticker.tick();
				//
			}
			j = j + 1;
		}
	}
	
	public String toString() {
		return "Problem Lecture";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(1, 100, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"Lecture", 
				"studio1.FromLecture", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
