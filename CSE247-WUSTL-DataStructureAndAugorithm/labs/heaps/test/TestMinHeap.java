package heaps.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;

import heaps.Decreaser;
import heaps.HeapSort;
import heaps.MinHeap;
import heaps.util.HeapToStrings;
import heaps.validate.MinHeapValidator;
import timing.InputSpec;
import timing.Ticker;
import timing.utils.IntArrayGenerator;

/**
 * 
 * @author roncytron and timhuber
 *
 */
public class TestMinHeap {

	private Random r = new Random();
	
	//
	// Fancy way to report problems, so our diagnostic output is included
	//
	@Rule
	public FailReporter tvs = new FailReporter();

	private static MinHeapValidator<Integer> genHeap(int size) {
		MinHeap<Integer> heap = new MinHeap<Integer>(size, new Ticker());
		return new MinHeapValidator<Integer>(heap);
	}
	
	@Test
	public void testEmptyHeap() {
		MinHeap<Integer> pq = genHeap(0).pq;	
		assertTrue("The heap after construction should be empty, but was not:  check your isEmpty() method", pq.isEmpty());
		assertEquals("The heap after construction should have 0 elements, but did not: check your size() method", 0, pq.size());
		verifySizeCapacity(pq);
	}

	@Test
	public void testInsert() {
		MinHeapValidator<Integer> mhv = genHeap(200);
		MinHeap<Integer> pq = mhv.pq;	
		int num = r.nextInt(100) + 1;
		for (int i=0; i < num; ++i) {
			verifySize("before insert", pq, i);
			verifySizeCapacity(pq);
			mhv.check();
			pq.insert(i);
			mhv.check();
			verifySize("after insert", pq, i+1);
			assertTrue("Heap should not be empty now: check your isEmpty() method", !pq.isEmpty());
		}
	}
	
	@Test
	public void testExtractMin() {
		MinHeapValidator<Integer> mhv = genHeap(200);
		MinHeap<Integer> pq = mhv.pq;	
		int n = r.nextInt(1000);
		int num = r.nextInt(100) + 1;
		for (int i=0; i < num; ++i) {
			mhv.check();
			pq.insert(n);
			mhv.check();
		}
		for (int i=0; i < num; ++i) {
			verifySizeCapacity(pq);
			verifySize("before extractMin", pq, num-i);
			mhv.check();
			int frompq = pq.extractMin();
			assertEquals("Should only see value " + n + " but saw value " + frompq,
					n, frompq);
			verifySize("after extractMin", pq,num-i-1);
			verifySizeCapacity(pq);
			mhv.check();
		}
		assertTrue("Heap should be empty now", pq.isEmpty());
	}
	
	@Test
	public void testUsingHeapSort() {
		HeapSort heaper = new HeapSort();
		heaper.loadInput(new IntArrayGenerator().genInput(InputSpec.gen(1000000)));
		heaper.reset(new Ticker());
		heaper.run();
	}

	private void verifySize(String event, MinHeap<?> pq, int expectedSize) {
		assertEquals("Expect queue " + event + " to have size " + expectedSize + " but it did not:  check your size() method", expectedSize, pq.size());
		
	}
	
	private void verifySizeCapacity(MinHeap<?> pq) {
		assertTrue("A heap's capacity must be at least its size. You have "
				+ " capacity: " + pq.capacity() + " size " + pq.size() + ".  Check your size() and capacity() methods",
				pq.capacity() >= pq.size());
	}
	
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
		for (int i=0; i < 10; ++i) {
			System.out.println("Testing with duplicates size " + i);
			sortTest(genDupInts(i), false);
			System.out.println("done");
		}
	}
	
	@Test
	public void testLocUpdatedDecrease() {
		//
		// Test that decrease updates the .loc field
		//
		MinHeapValidator<Integer> mhv = genHeap(5);
		MinHeap<Integer> pq = mhv.pq;
		mhv.check();
		Decreaser<Integer> d131 = pq.insert(131);
		mhv.check();
		String before = d131.toString();
		mhv.check();
		pq.insert(1); // should cause 131 to change locations
		mhv.check();
		String after = d131.toString();
		assertTrue("You did not update the .loc field of affected heap elements in decrease",
				!before.equals(after));
	}
	
	@Test
	public void testLocUpdatedHeapify() {
		//
		// Test that decrease updates the .loc field
		//
		MinHeapValidator<Integer> mhv = genHeap(10);
		MinHeap<Integer> pq = mhv.pq;
		for (int i=1; i <= 6; ++i) {
			mhv.check();
			pq.insert(i);
			mhv.check();
		}
		Decreaser<Integer> d131 = pq.insert(131);
		String before = d131.toString();
		mhv.check();
		pq.extractMin(); // should cause 131 to change locations
		mhv.check();
		String after = d131.toString();
		assertTrue("You did not update the .loc field of affected heap elements in heapify",
				!before.equals(after));
	}

	@Test
	public void testAndPrint() {
		System.out.println("Displaying a correct heap of 50 unique integers:");
		sortTest(genUniqueInts(50), true);
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

	private void sortTest(Iterable<Integer> iter, boolean show) {
		MinHeapValidator<Integer> mhv = genHeap(100);
		MinHeap<Integer> pq = mhv.pq;		
		int num = 0;
		assertEquals("Wrong result returned by your size() method:", 0, pq.size());
		for (Integer n : iter) {
			mhv.check();
			pq.insert(n);
			mhv.check();
			++num;
		}
		assertEquals("Wrong result returned by your size() method:", num, pq.size());
		if (show) {
			System.out.println(HeapToStrings.toTree(pq));
		}

		int[] copy = new int[num];
		int size = num;
		for (Integer n : iter) {
			copy[--num] = n;
		}
		Arrays.sort(copy);
		for (int i=0; i < size; ++i) {
			assertEquals("Wrong result returned by your size() method: ", (size-i), pq.size());
			mhv.check();
			int val = pq.extractMin();
			assertEquals("Extraction from queue violated sorted order ", copy[i], val);
			mhv.check();
		}

	}
}
