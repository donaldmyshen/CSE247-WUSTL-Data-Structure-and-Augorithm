package heaps;

import timing.Algorithm;
import timing.Ticker;

public class HeapSort implements Algorithm<Integer[],Integer[]> {
	
	private Integer[] originalArray, sortedArray;
	private MinHeap<Integer> heap;
	private Ticker ticker;
	
	public HeapSort() {
		
	}

	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.heap = new MinHeap<Integer>(originalArray.length, ticker);
		this.sortedArray = new Integer[originalArray.length];
	}

	@Override
	public void run() {
		for (Integer num : originalArray) {
			heap.insert(num);
			ticker.tick();
		}
		for (int i=0; i < originalArray.length; ++i) {
			int next = heap.extractMin();
			sortedArray[i] = next;
			ticker.tick();
		}
	}

	@Override
	public void loadInput(Integer[] input) {
		this.originalArray = input;
	}

	@Override
	public Integer[] getResults() {
		return this.sortedArray;
	}
	
	public String toString() {
		return originalArray == null ? "Heapsort" :
			"Heapsort of " + originalArray.length + " integers";
	}

}
