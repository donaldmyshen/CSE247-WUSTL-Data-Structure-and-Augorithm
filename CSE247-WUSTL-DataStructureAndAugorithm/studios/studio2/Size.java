package studio2;

import java.util.Random;

import studio2.lists.LinkedListWithTail;
import studio2.lists.List;
import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class Size extends QuietAlgorithm {
	
	protected Ticker ticker;
	protected List<Integer> list;
	private Random random;
	
	public Size() {
		this.random = new Random();
	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.list = new LinkedListWithTail<Integer>(ticker);
		for (int i=0; i < this.n; ++i) {
			list.addLast(random.nextInt());
		}
		//
		// reset the tick count to 0
		// we just want to measure the cost of doing getSize()
		//
		ticker.tick(-ticker.getTickCount());
	}

	@Override
	public void run() {
		int size = list.getSize();
	}

	public String toString() {
		return "Determining size of a list of " + this.n + " elements";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(0, 15000, 500);
		ExecuteAlgorithm.timeAlgorithm(
				"list-size", 
				"studio2.Size", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
