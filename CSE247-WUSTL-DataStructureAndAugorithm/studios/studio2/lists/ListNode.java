package studio2.lists;

//
// T is a parameterized type, could be anything:
//   ListNode<Color>   ListNode<Robot> ... and so on
//  if we didn't have this facility, we'd have to have a different ListNode
//    for each kind of value
//    ListNodeInteger
//    ListNodeColor
//    ListNodeRobot
//   gets messy
//
public class ListNode<T> {
	
	T           value;
	ListNode<T> next;
	
	public String toString() {
		return "ListNode with value " + value;
	}

}
