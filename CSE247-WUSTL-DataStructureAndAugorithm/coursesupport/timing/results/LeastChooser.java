package timing.results;

import java.util.PriorityQueue;

public class LeastChooser<T> implements ResultsChooser<T> {

	@Override
	public T getValue(PriorityQueue<T> pq) {
		return pq.poll();
	}

}
