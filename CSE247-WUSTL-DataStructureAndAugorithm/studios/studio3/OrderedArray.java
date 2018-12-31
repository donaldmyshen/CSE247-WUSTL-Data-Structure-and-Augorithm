package studio3;
public class OrderedArray<T extends Comparable<T>> implements PriorityQueue<T> {
	public T[] array;
	private int size;
	@SuppressWarnings("unchecked")
	public OrderedArray(int maxSize) {
		array = (T[]) new Comparable[maxSize];
		size = 0;
	}
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}}
	@Override
	public void insert(T thing) {
		if (size == 0) array[0] = thing;
		size++;
		int index = 0; 
		for (int i = size-2; i >= 0; i--) {
			if (thing.compareTo(array[i]) < 0) {
				index = i+1;
				break;
			}
		}
		for (int i = size-1; i > index; i--) {
			T temp = array[i];
			array[i] = array[i-1];
			array[i-1] = temp;
		}
	}
	@Override
	public T extractMin() {
		T min = array[size-1];
		array[size-1] = null;
		size--;
		return min;
	}
}