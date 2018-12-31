package studio2.lists;

import timing.Ticker;

public class LinkedList<T> implements List<T> {
	
	private ListNode<T> head;
	private Ticker ticker;
	
	public LinkedList(Ticker ticker) {
		this.head = null;   // nothing in our list yet
		this.ticker = ticker;
	}

	@Override
	public void addLast(T thing) {
		if (head == null) {
			ListNode<T> p = new ListNode<T>();
			p.value = thing;
			head = p;
			ticker.tick(3);  // for the 3 statements above
		}
		else {
			ListNode<T> q = new ListNode<T>();
			q.value = thing;
			// search for the end of the list
			//
			ListNode<T> p = head;
			while (p.next != null) {
				p = p.next;
				ticker.tick(2);
			}
			ticker.tick(1); // for the last test in the "while" loop
			
			p.next = q;
			ticker.tick(4);  // for the 4 statements not part of the "while" loop
		}
		ticker.tick(1); // for outermost "if" test
	}
	
	/**
	 * Return the size of this list by counting its elements
	 */
	@Override
	public int getSize() {
		int ans = 0;
		for (ListNode<T> p = this.head; p != null; p = p.next) {
			ans = ans + 1;
			ticker.tick();
		}
		return ans;
	}

	/**
	 * 
	 * @param n which time, 0 is the first item
	 * @return
	 */
	@Override
	public T getItemAt(int n) {
		ListNode<T> p = head;
		for (int i=0; i < n; ++i) {
			p = p.next;
			ticker.tick();
		}
		return p.value;
	}
	
	public String toString() {
		String ans = "[ ";
		for (ListNode<T> p = head; p != null; p = p.next) {
			ans = ans + p.value + " ";
		}
		ans = ans + "]";
		return ans;
	}



}
