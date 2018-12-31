package timing.utils;

import java.util.Iterator;

public class GenSizes implements Iterable<Integer> {
	
	private final int start, almostEnd;
	private final ProvidesNextValue pnp;

	private GenSizes(int start, int almostEnd, ProvidesNextValue pnp) {
		this.start     = start;
		this.almostEnd = almostEnd;
		this.pnp       = pnp;
	}
	
	public static GenSizes arithmetic(int start, int end, final int bump) {
		return new GenSizes(start, end, new ProvidesNextValue() {

			@Override
			public int nextValue(int oldValue) {
				return oldValue + bump;
			}
			
		});
	}
	
	public static GenSizes geometric(int start, int end, final int bump) {
		return new GenSizes(start, end, new ProvidesNextValue() {

			@Override
			public int nextValue(int oldValue) {
				return oldValue * bump;
			}
			
		});
	}
	
	public static GenSizes arithmetic() {
		return arithmetic(0,100,1);
	}
	
	public static GenSizes singleValue(int value) {
		return arithmetic(value, value+1,1);
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer> () {
			
			private int i = start;

			@Override
			public boolean hasNext() {
				return i < almostEnd;
			}

			@Override
			public Integer next() {
				int ans = i;
				i = pnp.nextValue(i);
				return ans;
			}
			
		};
	}

}
