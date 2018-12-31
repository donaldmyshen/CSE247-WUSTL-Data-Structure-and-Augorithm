package timing.utils;

import java.util.Random;

import timing.InputSpec;
import timing.InputProvider;

public class IntArrayGenerator implements InputProvider<Integer[]> {

	@Override
	public Integer[] genInput(InputSpec size) {
		Random r = new Random();
		Integer[] ans = new Integer[size.getFirstParameter()];
		for (int i=0; i < ans.length; ++i) {
			ans[i] = r.nextInt();
		}
		return ans;
	}

}
