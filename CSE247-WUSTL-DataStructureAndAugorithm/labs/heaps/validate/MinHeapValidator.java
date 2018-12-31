package heaps.validate;

import heaps.Decreaser;
import heaps.MinHeap;
import heaps.util.HeapToStrings;

public class MinHeapValidator<K extends Comparable<K>> {
	final public MinHeap<K> pq;
	private String before;

	public MinHeapValidator(final MinHeap<K> pq) {
		this.pq = pq;
		this.before = pq.toString();

		//
		//Every time the heap is altered, check to make sure that no
		//min-heap properties are violated
		//
	}
	
	/**
	 * The instance variable "before" captures the state of the heap
	 * last time we looked.   This method runs our validation methods,
	 * and if something goes wrong, it reports the before tree, the error,
	 * and the after tree.  This should provide enough information to
	 * diagnose your problems with your binary heap implementation.
	 */
	public void check() {
		try {
			checkForGaps();
			childrenNoSmallerThanParent();
			checkLocsCorrect();
			before = HeapToStrings.toTree(pq);
		} catch(Throwable t) {
			String oops = "\nTree before the problem occurred:\n";
			oops += before + "\n";
			oops += "What went wrong: " + t.getMessage() + "\n";
			// System.out.println("Its stack trace is ");
			// t.printStackTrace();
			oops += "Tree that triggered this problem:" + "\n";
			oops += HeapToStrings.toTree(pq);
			t.printStackTrace();
			throw new HeapValidationError(t + "" + oops);
		}
	}





	/**
	 * Scan the heap and make sure that no child's value exceeds its
	 * parent's value.   Recall from lecture that empty nodes in the binary
	 * heap tree are essentially infinite, so we only have to check a child
	 * that is in the active part of the heap.
	 */
	public void childrenNoSmallerThanParent() {
		//
		// Loop while the node at i is not a leaf. 
		//
		for (int i=1; 2*i <= pq.size(); ++i) {
			String err = "";

			if (2*i + 1 <= pq.size()) {
				if(pq.peek(i).compareTo(pq.peek(2*i + 1)) > 0) {
					err = "The node at index " 
							+ i 
							+ " is larger than its right child: "
							+ pq.peek(i) + ">" + pq.peek(2*i+1)
							+ "\n"
							;
				}
			}

			if(pq.peek(i).compareTo(pq.peek(2*i)) > 0) {
				err = err + "The node at index " 
						+ i 
						+ " is larger than its left child: "
						+ pq.peek(i) + ">" + pq.peek(2*i) 
						+ "\n"
						;			
			}
			if (!err.equals("")) {
				throw new Error(err);
			}

		}
	}

	/**
	 * Make sure that the binary heap is "nearly complete" as described
	 * in lecture.   This consists of two thing:
	 *   1)  There should be no null entries in the currently active part
	 *       of your heap array.   The range of the active heap is
	 *       from 1...pq.size() inclusively.
	 *   2)  In the inactive part of the heap, all of the elements there
	 *       should be null.  The range of the inactive part is
	 *       from pq.size()+1...pq.capacity() inclusively.
	 *       
	 *       You might say, and you'd be right, that your heap would function
	 *       correctly even if those elements in the inactive part are *not* null.
	 *       However, references from the heap contribute to the liveness of
	 *       their referenced objects.  Objects with live references cannot be
	 *       garbage collected, and we do not want the binary heap to be responsible
	 *       for artificially extending the life of otherwise dead objects.
	 */
	public void checkForGaps() {
		//
		// No nulls in active part of heap
		//
		for (int i=1; i <= pq.size(); ++i) {
			if (pq.peek(i) == null)
				throw new Error("I found a null entry within the currently occupied portion of the heap, at index " + i);
		}

		//
		// Only nulls in inactive part of heap
		//
		for (int i=pq.size()+1; i <= pq.capacity(); ++i) {
			if (pq.peek(i) != null) {
				throw new Error("The min heap is not null at index " + i + ", which is"
						+ " past the last element in the heap.");
			}
		}
	}
	
	/**
	 * Check that each Decreaser object has the proper location
	 *   information.
	 */
	public void checkLocsCorrect() {
		for (int i=1; i <= pq.size(); ++i) {
			int loc = pq.getLoc(i);
			if (loc != i) {
				throw new Error("In your array at index " + i + 
						" the Decreaser object has the wrong location."
						+ " It should be " + i + " but was " + loc);
			}
		}
	}
}
