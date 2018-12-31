package studio1;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class FromLastStudio extends QuietAlgorithm {

	private Ticker ticker;
	public  int    value;

	public FromLastStudio() {
	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.value = 0;
	}

	/**
	 * Create a quadratic amount of work,
	 *   using two nested loops each of n iterations.
	 */
	@Override
	public void run() {
		ticker.tick();                      // i = 0
		for (int i=0; i < n; ++i) {
			ticker.tick();                  // i < n
			ticker.tick();                      // j = 0
			for (int j=0; j < n; ++j) {
				ticker.tick();                  // j < n
                                //
                                // original one below
                                //
				ticker.tick();                     // add two values
				this.value = this.value + i;
				ticker.tick();                  // ++j
			}
			ticker.tick();                  // ++i
		}	
	}

	public String toString() {
		return "From Last Studio " + n;
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(10000, 100000, 10000);
		ExecuteAlgorithm.timeAlgorithm(
				"laststudio", 
				"studio1.FromLastStudio", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
