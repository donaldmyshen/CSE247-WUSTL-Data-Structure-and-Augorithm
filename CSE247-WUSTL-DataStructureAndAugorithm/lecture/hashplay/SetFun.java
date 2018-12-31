package hashplay;

import java.util.HashSet;
import java.util.Set;

public class SetFun {
	
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(3);
		set.add(3);
		set.add(4);
		
		System.out.println(set);
		
		Set<BadInt> bset = new HashSet<BadInt>();
		
		BadInt b1 = new BadInt(3);
		BadInt b2 = new BadInt(3);
		
		bset.add(b1);
		bset.add(b2);
		bset.add(new BadInt(247));
		
		System.out.println(bset);
		
		
	}

}
