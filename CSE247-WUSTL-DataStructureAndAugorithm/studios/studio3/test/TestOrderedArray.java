package studio3.test;

import studio3.OrderedArray;
import studio3.PriorityQueue;

public class TestOrderedArray extends TestBase {

	@Override
	public PriorityQueue<Integer> genHeap(int size) {
		return new OrderedArray<Integer>(size);
	}

}
