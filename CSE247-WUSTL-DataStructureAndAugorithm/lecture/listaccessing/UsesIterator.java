package listaccessing;

import java.util.Iterator;
import java.util.LinkedList;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class UsesIterator extends QuietAlgorithm {
	
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
		// easy way
//		for (Integer i : list) {  // shorthand for using java iterator
//			int value = i;
//		}
		// long way to say it
		int count = 0;
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			int value = iter.next();
			ticker.tick();
			++count;
		}
		
	}
	
	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(10000, 1000000, 10000);
		ExecuteAlgorithm.timeAlgorithm(
				"usesiterator", 
				"listaccessing.UsesIterator", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
