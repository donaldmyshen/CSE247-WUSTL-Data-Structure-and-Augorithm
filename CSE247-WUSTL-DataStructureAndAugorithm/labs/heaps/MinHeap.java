package heaps;
// Name: Mingyuan Shen
// ID: 465048
import java.util.Random;
import java.util.UUID;

import javax.swing.JOptionPane;

import heaps.util.HeapToStrings;
import heaps.validate.MinHeapValidator;
import timing.Ticker;

public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T> {

	private Decreaser<T>[] array;
	private int size;
	private final Ticker ticker;

	/**
	 * I've implemented this for you.  We create an array
	 *   with sufficient space to accommodate maxSize elements.
	 *   Remember that we are not using element 0, so the array has
	 *   to be one larger than usual.
	 * @param maxSize
	 */
	@SuppressWarnings("unchecked")
	public MinHeap(int maxSize, Ticker ticker) {
		this.array = new Decreaser[maxSize+1];
		this.size = 0;
		this.ticker = ticker;
		
	}

	//
	// Here begin the methods described in lecture
	//
	
	/**
	 * Insert a new thing into the heap.  As discussed in lecture, it
	 *   belongs at the end of objects already in the array.  You can avoid
	 *   doing work in this method by observing, as in lecture, that
	 *   inserting into the heap is reducible to calling decrease on the
	 *   newly inserted element.
	 *   
	 *   This method returns a Decreaser instance, which for the inserted
	 *   thing, tracks the thing itself, the location where the thing lives
	 *   in the heap array, and a reference back to MinHeap so it can call
	 *   decrease(int loc) when necessary.
	 */
	public Decreaser<T> insert(T thing) {
		//
		// Below we create the "handle" through which the value of
		//    the contained item can be decreased.
		// VERY IMPORTANT!
		//    The Decreaser object contains the current location
		//    of the item in the heap array.  Initially it's ++size,
		//    as shown below.  The size is increased by 1, and that's
		//    were you should store ans in the heap array.
		//
		//    If and when the element there changes location in the heap
		//    array, the .loc field of the Decreaser must change to reflect
		//    that.
		//
		Decreaser<T> ans = new Decreaser<T>(thing, this, ++size);
	    // insert a new element into heap, reduce insert to decrease 
		array[size]=ans;
		decrease(size);
		ticker.tick(2);
		return ans;
	}

	/**
	 * This method responds to an element in the heap decreasing inq'w
	 * value.   As described in lecture, that element might have to swap
	 * its way up the tree so that the heap property is maintained.
	 * 
	 * This method can be called from within this class, in response
	 *   to an insert.  Or it can be called from a Decreaser.
	 *   The information needed to call this method is the current location
	 *   of the heap element (index into the array) whose value has decreased.
	 *   
	 * Really important!   If this method changes the location of elements in
	 *   the array, then the loc field within those elements must be modified 
	 *   too.  For example, if a Decreaser d is currently at location 100,
	 *   then d.loc == 100.  If this method moves that element d to
	 *   location 50, then this method must set d.loc = 50.
	 *   
	 * In my solution, I made sure the above happens by writing a method
	 *    moveItem(int from, int to)
	 * which moves the Decreaser from index "from" to index "to" and, when
	 * done, sets array[to].loc = to
	 *   
	 * This method is missing the "public" keyword so that it
	 *   is only callable within this package.
	 * @param loc position in the array where the element has been
	 *     decreased in value
	 */
	void decrease(int loc) {
		Decreaser<T> decValue = array[loc];
		Decreaser <T> parentValue = array[loc/2];
		ticker.tick(2);
		if(loc==0 || loc==1 || decValue.getValue().compareTo(parentValue.getValue())>=0 ) {
			return;
		}// If location is 0 or 1 or child's value is bigger than parent's value, return
		else if(decValue.getValue().compareTo(parentValue.getValue())<0) {
			Decreaser <T> decLoc = array[loc];
			array[loc]=array[loc/2];
			// Change the position of the elements 
			array[loc/2] = decLoc;
			array[loc].loc = loc;
			array[loc/2].loc = loc/2;
			// Change the location information of the new swapped elements 
			// A parent has two children, a node has two leaves 
			ticker.tick(5);
			decrease(loc/2);
			}
	}
	
	/**
	 * Described in lecture, this method will return a minimum element from
	 *    the heap.  The hole that is created is handled as described in
	 *    lecture.
	 *    This method should call heapify to make sure the heap property is
	 *    maintained at the root node (index 1 into the array).
	 */
	public T extractMin() {
		T ans = array[1].getValue();
		array[1]=array[size];
		// Move the last element to the root
		array[1].loc=1;
		array[size] = null; 
		size--;
		// Remove the spare node (element position)
		heapify(1);
		ticker.tick(6);
		return ans;
	}

	/**
	 * As described in lecture, this method looks at a parent and its two 
	 *   children, imposing the heap property on them by perhaps swapping
	 *   the parent with the lesser of the two children.  The child thus
	 *   affected must be heapified itself by a recursive call.
	 * @param where the index into the array where the parent lives
	 */
	private void heapify(int where) {
		// As I mentioned in preLab, we use where as an index to show the relation of position
		// parent=where, childA = where*2 and childB = where*2+1
		// Copare the parent with childA first, then childB
		if(where*2 <= size) {
			// Check if there is childA
			Decreaser<T> childA = array[where*2];
			Decreaser<T> parent = array[where];
			// If childA exist, parent must exist 
			ticker.tick(2);
			if(where*2 +1 <= size) {
				// Check if there is childB exist
				Decreaser<T> childB = array[where*2+1];
				ticker.tick(1);
				if(childA.getValue() != null && childB.getValue() != null) {
					// Check childA and childB have values 
					if(childA.getValue().compareTo(childB.getValue())>= 0) {
						// Compare childA and childB, if A>B
						if( parent.getValue() == null || parent.getValue().compareTo(childB.getValue()) >= 0 ){
							// Compare childB with parent, if smaller than parent, swap
							array[where] = childB;
							array[where*2+1] = parent;
							array[where].loc=where;
							array[where*2+1].loc=where*2+1;
							ticker.tick(4);
							heapify(where*2+1);
						}
					}
					else{ 
						// Else childB > childA
						if( parent.getValue().compareTo(childA.getValue()) >= 0 ){
						//Compare childA with parent, if smaller than parent, swap
						array[where] = childA;
						array[where*2] = parent;
						array[where].loc=where;
						array[where*2].loc=where*2;
						ticker.tick(4);
						heapify(where*2);
						}	
					}	
				}
			}
			else if(parent.getValue() == null ||parent.getValue().compareTo(childA.getValue()) >= 0 ){
			// If childB not exist, we directly compare childA with parent
				array[where] = childA;
				array[where*2] = parent;
				array[where].loc=where;
				array[where*2].loc=where*2;
				ticker.tick(4);
				heapify(where*2);
				}
			}	
		}	
		
	/**
	 * Does the heap contain anything currently?
	 * I implemented this for you.  Really, no need to thank me!
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	//
	// End of methods described in lecture
	//
	
	//
	// The methods that follow are necessary for the debugging
	//   infrastructure.
	//
	/**
	 * This method would normally not be present, but it allows
	 *   our consistency checkers to see if your heap is in good shape.
	 * @param loc the location
	 * @return the value currently stored at the location
	 */
	public T peek(int loc) {
		if (array[loc] == null)
			return null;
		else return array[loc].getValue();
	}

	/**
	 * Return the loc information from the Decreaser stored at loc.  They
	 *   should agree.  This method is used by the heap validator.
	 * @param loc
	 * @return the Decreaser's view of where it is stored
	 */
	public int getLoc(int loc) {
		return array[loc].loc;
	}

	public int size() {
		return this.size;
	}
	
	public int capacity() {
		return this.array.length-1;
	}
	

	/**
	 * The commented out code shows you the contents of the array,
	 *   but the call to HeapToStrings.toTree(this) makes a much nicer
	 *   output.
	 */
	public String toString() {
//		String ans = "";
//		for (int i=1; i <= size; ++i) {
//			ans = ans + i + " " + array[i] + "\n";
//		}
//		return ans;
		return HeapToStrings.toTree(this);
	}

	/**
	 * This is not the unit test, but you can run this as a Java Application
	 * and it will insert and extract 100 elements into the heap, printing
	 * the heap each time it inserts.
	 * @param args
	 */
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "You are welcome to run this, but be sure also to run the TestMinHeap JUnit test");
		MinHeap<Integer> h = new MinHeap<Integer>(500, new Ticker());
		MinHeapValidator<Integer> v = new MinHeapValidator<Integer>(h);
		Random r = new Random();
		for (int i=0; i < 100; ++i) {
			v.check();
			h.insert(r.nextInt(1000));
			v.check();
			System.out.println(HeapToStrings.toTree(h));
			//System.out.println("heap is " + h);
		}
		while (!h.isEmpty()) {
			int next = h.extractMin();
			System.out.println("Got " + next);
		}
	}


}
