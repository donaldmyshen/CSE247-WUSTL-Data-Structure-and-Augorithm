package studio2;

import studio2.lists.LinkedListWithTail;
import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class AddLastWithTail extends AddLastBase {
		
	public AddLastWithTail() {
	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.list = new LinkedListWithTail<Integer>(ticker);
	}
	
	public String toString() {
		return "List with tail appending " + this.n + " integers";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(0, 15000, 500);
		ExecuteAlgorithm.timeAlgorithm(
				"list-addlast-withtail", 
				"studio2.AddLastWithTail", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
