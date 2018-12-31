package timing.results;

import java.util.PriorityQueue;

public interface ResultsChooser<T> {
	
	public T getValue(PriorityQueue<T> pq);

}
