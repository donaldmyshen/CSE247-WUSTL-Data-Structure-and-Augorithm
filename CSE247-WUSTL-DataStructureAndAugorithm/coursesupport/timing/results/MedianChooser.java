package timing.results;

import java.util.PriorityQueue;

public class MedianChooser<T> implements ResultsChooser<T>{

	@Override
	public T getValue(PriorityQueue<T> pq) {
		int numTimes = pq.size();
		T ans = null;
		for (int i=0; i <= numTimes/2; ++i) {
			ans = pq.poll();
		}
		return ans;
	}

}
