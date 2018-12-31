package studio2;

import java.util.Random;

import studio2.lists.List;
import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

abstract public class AddLastBase extends QuietAlgorithm {
	
	protected Ticker ticker;
	protected List<Integer> list;
	private Random random;
	
	public AddLastBase() {
		this.random = new Random();
	}

	@Override
	abstract public void reset(Ticker ticker);

	@Override
	public void run() {
		for (int i=0; i < this.n; ++i) {
		    list.addLast(random.nextInt());
		}
	}

}
