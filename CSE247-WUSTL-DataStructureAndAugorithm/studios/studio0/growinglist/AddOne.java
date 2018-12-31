package studio0.growinglist;

import timing.ExecuteAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class AddOne extends Rarrays {

	/**
	 * We ask for a new, bigger array that is just one element larger
	 *   than the current one.
	 */
	@Override
	public int getNewSize() {
		return array.length + 1;
		
	}
	
	public String toString() { return "Grow by one"; }

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(1, 1000, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"growbyone", 
				"studio0.growinglist.AddOne", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
