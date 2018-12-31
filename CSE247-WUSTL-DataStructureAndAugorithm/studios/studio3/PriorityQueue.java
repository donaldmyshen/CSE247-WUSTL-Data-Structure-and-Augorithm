package studio3;

public interface PriorityQueue<T extends Comparable<T>> {
	
	public boolean isEmpty();
	public void insert(T thing);
	public T extractMin();

}

//public class Test { 
//	   public static void main(String args[]){
//	      Integer a = 1;
//	      Integer b = 5;
//	      System.out.println(a.compareTo(b));
	                  
//	     }
//	}