package timing.utils;

import timing.InputProvider;
import timing.InputSpec;

public class IntSequenceArrayGenerator implements InputProvider<Integer[]> {

	@Override
	public Integer[] genInput(InputSpec size) {
		
		Integer[] ans = new Integer[size.getFirstParameter()];
		for (int i=0; i < ans.length; ++i) {
			ans[i] = i;
		}
		return ans;
	}

}
