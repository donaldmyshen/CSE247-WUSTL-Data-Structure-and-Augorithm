package studio0.growinglist;

import timing.ExecuteAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class Doubling extends Rarrays {

	/**
	 * We ask for a new, bigger array that is twice as large as the
	 * current one.
	 */
	@Override
	public int getNewSize() {
		return 2 * array.length;
	}
	
	public String toString() { return "Grow by doubling"; }

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(1, 1000, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"doubling", 
				"studio0.growinglist.Doubling", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
