package studio2;

import studio2.lists.LinkedList;
import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class AddLastNoTail extends AddLastBase {
		
	public AddLastNoTail() {
	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.list = new LinkedList<Integer>(ticker);
	}
	
	public String toString() {
		return "List without tail appending " + this.n + " integers";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(0, 15000, 500);
		ExecuteAlgorithm.timeAlgorithm(
				"list-addlast-notail", 
				"studio2.AddLastNoTail", 
				new IntArrayGenerator(),  
				sizes
				);	
	}

}
