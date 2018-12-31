package heaps;

public interface PriorityQueue<T extends Comparable<T>> {
	
	public boolean isEmpty();
	public Decreaser<T> insert(T thing);
	public T extractMin();

}
