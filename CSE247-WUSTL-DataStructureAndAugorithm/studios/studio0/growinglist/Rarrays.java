package studio0.growinglist;

import timing.Ticker;
import timing.quiet.QuietAlgorithm;

/**
 * Measure the time (ticks) needed to replace a full array with a new, bigger one.
 * How much bigger should the new array be?
 * You examine several strategies for sizing the new, bigger array
 * and you study the effects of those strategies on overall execution
 * time (ticks).
 * @author roncytron
 *
 */
abstract public class Rarrays extends QuietAlgorithm {
	
	protected int[] array;
	protected Ticker ticker;
	
	public Rarrays() {
		
	}

	/**
	 * Begin with an array of two elements.
	 */
	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
		this.array  = new int[2];
		ticker.tick(2);
	}

	/**
	 * As a "quiet" algorithm, all we care about is the size
	 * parameter.  So we keep filling the current array until we
	 * have a total of size elements.  But along the way, the array
	 * gets full.  When it does, we replace the old array with a bigger
	 * one.
	 */
	@Override
	public void run() {
		for (int i=0; i < n; ++i) {
			if (i >= array.length)
				replaceArrayWithBiggerOne();
			array[i] = i;
			ticker.tick();
		}
	}
	
	/**
	 * Use getNewSize() to obtain how much larger the new, bigger
	 * array should be.  Then copy all of the current elements into
	 * that new array.  We then set the instance variable
	 * array reference to the new, bigger array, so the code above
	 * can continue filling the array using the bigger one.
	 */
	protected void replaceArrayWithBiggerOne() {
		//
		//  The newSize value is supplied by the particular extending
		//    class that you run.
		//
		int newSize = getNewSize();
		//
		//  Make a new array of the new size
		//       This should be a local variable, not an instance variable
		//
		
		//  tick appropriately to account for Java initializing
		//    the elements of this new array to 0
		//
		
		//
		//  Copy the elements of the current array to the new one
		//    tick appropriately to account for each copied element
		//
		//
		
		//  Assign your local array to the array reference, 
		//    so that code elsewhere using the "array" instance variable
		//    will now reference the new, bigger one
		//
	}
	
	/**
	 * This is abstract, so that extending classes can try their own
	 * strategies about how much larger the new array should be.
	 * @return the size of the new array that will replace the current one
	 */
	abstract public int getNewSize();
	
}
