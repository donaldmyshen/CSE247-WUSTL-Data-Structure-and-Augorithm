package studio3.test;

import studio3.PriorityQueue;
import studio3.UnorderedList;

public class TestUnorderedList extends TestBase {

	@Override
	public PriorityQueue<Integer> genHeap(int size) {
		return new UnorderedList<Integer>();
	}

}
