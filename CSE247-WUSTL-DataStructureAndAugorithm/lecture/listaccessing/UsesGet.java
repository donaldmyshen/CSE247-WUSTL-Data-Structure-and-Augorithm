package listaccessing;

import java.util.LinkedList;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class UsesGet extends QuietAlgorithm {
	
	private LinkedList<Integer> list;
	private Ticker ticker;

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		// create a list of n items
		list = new LinkedList<Integer>();
		for (int i=0; i < this.n; ++i) {
			list.add(i);
		}
		
	}

	@Override
	public void run() {
		// access the n items one at a time
		// use get to get them, not the iterator
		for (int i=0; i < n; ++i) {
			int value = list.get(i);
			ticker.tick(i); // wrong :  getting the ith element takes i ticks
		}
		
	}
	
	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(10000, 100000, 10000);
		ExecuteAlgorithm.timeAlgorithm(
				"usesget", 
				"listaccessing.UsesGet", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
