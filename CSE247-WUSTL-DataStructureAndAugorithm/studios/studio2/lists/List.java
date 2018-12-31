package studio2.lists;

public interface List<T> {
	
	public void addLast(T thing);
	
	public T getItemAt(int n);
	
	public int getSize();
	

	//
	// normally we'd also have:
	//  addFirst, size(), isEmpty(), ....  --- the stuff you'd want to do with a List

}
