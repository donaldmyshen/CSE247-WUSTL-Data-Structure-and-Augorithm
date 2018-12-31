package timing.quiet;

import timing.Algorithm;

abstract public class QuietAlgorithm implements Algorithm<Integer[],NoOutputNeeded>{


	protected int n;
	
	@Override
	public void loadInput(Integer[] input) {
		this.n = input.length;
	}

	@Override
	public NoOutputNeeded getResults() {
		return null;
	}

}
