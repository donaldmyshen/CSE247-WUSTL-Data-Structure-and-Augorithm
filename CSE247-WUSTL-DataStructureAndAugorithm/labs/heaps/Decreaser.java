package heaps;

/**
 * A front office for the MinHeap to handle decrease calls
 * 
 * @author roncytron
 *
 * @param <T>
 */
public class Decreaser<T extends Comparable<T>> {
	
	private T thing;
	final private MinHeap<T> heap;
	//
	// Really important!  The value of loc must be maintained by MinHeap so
	//   that it always reflects the current location of this thing.  So
	//   if the thing moves up or down the heap tree, the value of loc
	//   must be updated appropriately.
	//
	//   The value of loc can be set from within heap because loc is not private
	//   here:  it is available anywhere in this package.
	int loc;
	
	/**
	 * 
	 * @param thing the thing inserted into the heap
	 * @param h a reference back to the heap, so decrease can notify the heap appropriately
	 * @param loc the current location of the thing in the heap array.
	 */
	public Decreaser(T thing, MinHeap<T> h, int loc) {
		this.thing = thing;
		this.heap  = h;
		this.loc   = loc;
	}
	
	/**
	 * Handle requests for decreasing the item at location loc
	 * @param newvalue the new value for the item at location loc
	 */
	public void decrease(T newvalue) {
		if (newvalue.compareTo(thing) < 0) {
			this.thing = newvalue;
			//
			// Now that the value has decreased, it may need to move
			//   toward the root to maintain the heap property
			//
			heap.decrease(loc);
		}
		else if (newvalue.compareTo(thing) == 0) {
			// do nothing, same value
		}
		else throw new IllegalArgumentException("Value was " + thing + " and cannot be decreased to " + newvalue);
	}
	
	public T getValue() {
		return this.thing;
	}
	
	public String toString() {
		return "" + thing + " at " + loc;
	}

}
