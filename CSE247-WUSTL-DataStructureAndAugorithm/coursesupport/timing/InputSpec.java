package timing;

import java.util.Arrays;

/**
 * Describes the size of input for an algorithm.  Accommodates multiple
 *   parameters, for algorithms that are O(n,m) etc.
 * Typically for CSE247, we just need one parameter
 * @author roncytron
 *
 */
public class InputSpec {
	
	private final int[] params;
	
	private InputSpec(int[] params) {
		this.params = Arrays.copyOf(params, params.length);
	}
	
	private InputSpec(int one) {
		this(new int[] { one });
	}
	
	private InputSpec(int one, int two) {
		this(new int[] { one, two });
	}
	
	public int getFirstParameter() {
		return params[0];
	}
	
	public int getSecondParameter() {
		return params[1];
	}
	
	public int[] getParamters() {
		return Arrays.copyOf(this.params, this.params.length);
	}
	
	// Factory methods
	
	public static InputSpec gen(int one) {
		return new InputSpec(one);
	}
	
	public static InputSpec gen(int one, int two) {
		return new InputSpec(one, two);
	}
	
	public String toString() {
		String ans = "(" + params[0];
		for (int i=1; i < params.length; ++i) {
			ans += "," + params[i];
		}
		ans += ")";
		return ans;
	}

}
