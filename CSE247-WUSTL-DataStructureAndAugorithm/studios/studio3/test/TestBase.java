package studio3.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

import studio3.PriorityQueue;

/**
 * 
 * @author roncytron
 *
 */
abstract public class TestBase {

	private Random r = new Random();
	
	//
	//Test the insert and extract min methods of the priority queue by generating 
	//an unsorted array of integers, inserting that array into a priority queue, and
	//then comparing the output of extractMin() with the respective value in the
	//sorted starting array
	//
	@Test
	public void testViaSorting() {
		for (int i=0; i < 10; ++i) {
			System.out.println("Testing unique size " + i);
			sortTest(genUniqueInts(i), false);
			System.out.println("done");
		}
	}
	

	private Iterable<Integer> genUniqueInts(int num) {
		ArrayList<Integer> ans = new ArrayList<Integer>(num);
		for (int i=0; i < num; ++i) {
			ans.add(i,i);
		}
		Collections.shuffle(ans);
		return ans;
	}

	private Iterable<Integer> genDupInts(int num) {
		ArrayList<Integer> ans = new ArrayList<Integer>(num);
		for (int i=0; i < num; ++i) {
			ans.add(i, r.nextInt(num/2+1));
		}
		Collections.shuffle(ans);
		return ans;
	}
	
	abstract public PriorityQueue<Integer> genHeap(int size);

	private void sortTest(Iterable<Integer> iter, boolean show) {
		PriorityQueue<Integer> pq = genHeap(100);
		int num = 0;
		for (Integer n : iter) {
			pq.insert(n);
			++num;
		}

		int[] copy = new int[num];
		int size = num;
		for (Integer n : iter) {
			copy[--num] = n;
		}
		Arrays.sort(copy);
		for (int i=0; i < size; ++i) {
			int val = pq.extractMin();
			assertEquals("Extraction from queue violated sorted order ", copy[i], val);
		}

	}
}
