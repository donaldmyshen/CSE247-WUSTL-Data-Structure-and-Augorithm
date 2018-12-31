package studio3;
import java.util.LinkedList;
import java.util.List;
public class UnorderedList<T extends Comparable<T>> implements PriorityQueue<T> {
	public List<T> list;
	public UnorderedList() {
		list = new LinkedList<T>();
	}
	@Override
	public boolean isEmpty() {
		if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void insert(T thing) {
		list.add(thing);
	}
	@Override
	public T extractMin() {
		int index = 0; T min = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).compareTo(min) < 0) {
				index = i;
				min = list.get(i);
			}
		}
		return list.remove(index);
	}
}