package timing.examples;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class Log extends QuietAlgorithm {
	
	private Ticker ticker;
	
	public Log() {
	}
	
	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
	}

	/**
	 * Kick off the helper with the size parameter n
	 */
	@Override
	public void run() {
		helper(n);
	}
	
	private void helper(int size) {
		ticker.tick();       // each side of the if takes one operation, let's say
		if (size <= 1)
			return;
		else {
			helper(size/2);
		}
	}

	public String toString() {
		return "Logarithmic " + n;
	}
	
	public static void main(String[] args) {
		GenSizes sizes = GenSizes.geometric(1, 1000000, 2);
		ExecuteAlgorithm.timeAlgorithm(
				"log", 
				"timing.examples.Log", 
				new IntArrayGenerator(), 
				sizes
				);
	}

}
