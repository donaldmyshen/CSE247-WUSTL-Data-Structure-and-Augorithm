package hash;

//
// RECORD.JAVA
// Record type for string hash table
//
// A record associates a certain string (the key) with a list of
// sequence positions at which that string occurs.
//

import java.util.ArrayList;

public class Record {
    
    public String key;
    public ArrayList<Integer> positions;
    
    public Record(String s)
    {
	key = s;
	positions = new ArrayList<Integer>(1);
    }
}
