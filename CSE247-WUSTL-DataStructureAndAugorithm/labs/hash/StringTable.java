package hash;

import java.util.LinkedList;
import java.util.ListIterator;

//
// STRINGTABLE.JAVA
// A hash table mapping Strings to their positions in the the pattern sequence
// You get to fill in the methods for this part.
//
public class StringTable {
    
    private LinkedList<Record>[] buckets;
    private int nBuckets;

    //
    // number of records currently stored in table --
    // must be maintained by all operations
    //
    public int size;
    
    
    //
    // Create an empty table with nBuckets buckets
    //
    @SuppressWarnings("unchecked")
	public StringTable(int nBuckets)
    {
    	this.nBuckets = nBuckets;
    	buckets = new LinkedList[nBuckets];
    	for (int i = 0; i < nBuckets; i++) {
    		buckets[i] = new LinkedList<Record>();
    	}
    }  
    /**
     * insert - inserts a record to the StringTable
     *
     * @param r
     * @return true if the insertion was successful, or false if a
     *         record with the same key is already present in the table.
     */
    public boolean insert(Record r) 
    {
    	String rkey = r.key;
    	int rHashCode = stringToHashCode(rkey);
    	int indexBucket = toIndex(rHashCode);
    	LinkedList<Record> currentBucket = buckets[indexBucket];
    	// Corner case
    	if (currentBucket.isEmpty() == true) {
    		currentBucket.add(r);
    		size = size + 1;
    		return true;
    	}
    	ListIterator<Record> Itr = currentBucket.listIterator();
    	while (Itr.hasNext()) {
    		Record rInBucket = Itr.next();
    		if (rkey.equals(rInBucket.key)) {
    			return false;
    		}
    	}
    	currentBucket.addLast(r);
    	size += 1;
    	return true;
    }  
    /**
     * find - finds the record with a key matching the input.
     *
     * @param key
     * @return the record matching this key, or null if it does not exist.
     */
    public Record find(String key) 
    {
    	int rHashCode1 = stringToHashCode(key);
    	int indexBucket1 = toIndex(rHashCode1);
    	LinkedList<Record> currentBucket1 = buckets[indexBucket1];
    	// Corner case
    	if (currentBucket1.isEmpty() == true) {
    		return null;
    	}
    	ListIterator<Record> itr1 = currentBucket1.listIterator();
    	   while (itr1.hasNext()) {
    		   Record rInBucket1 = itr1.next();
    		   if (key.equals(rInBucket1.key)) {
    			   return rInBucket1;
    		   } 	
    	   } 
		   return null;
    } 
    /**
     * remove - finds a record in the StringTable with the given key
     * and removes the record if it exists.
     *
     * @param key
     */
    public void remove(String key) 
    {
    	int rHashCode2 = stringToHashCode(key);
    	int indexBucket2 = toIndex(rHashCode2);
    	LinkedList<Record> currentBucket2 = buckets[indexBucket2];	
    	if (currentBucket2.isEmpty() == false) {
    		ListIterator<Record> itr2 =currentBucket2.listIterator();
    		while (itr2.hasNext()) {
    			Record rInBucket2 = itr2.next();
    			if (key.equals(rInBucket2.key)) {
    				size -= 1;
    				currentBucket2.remove(rInBucket2);
    				break;
    			}
    		}	
    	}  	
    }
    /**
     * toIndex - convert a string's hashcode to a table index
     *
     * As part of your hashing computation, you need to convert the
     * hashcode of a key string (computed using the provided function
     * stringToHashCode) to a bucket index in the hash table.
     *
     * You should use a multiplicative hashing strategy to convert
     * hashcodes to indices.  If you want to use the fixed-point     
     * computation with bit shifts, you may assume that nBuckets is a
     * power of 2 and compute its log at construction time.
     * Otherwise, you can use the floating-point computation.
     */
    private int toIndex(int hashcode)
    {
    	//set x=((5^(-2))-1/)2. It is an infinite non-repeating decimal.
    	double x=(Math.sqrt(5)-1)/2;
    	int index=Math.abs((int)(((hashcode*x)%1.0)*nBuckets));
    	return index;
    } 
    /**
     * stringToHashCode
     * Converts a String key into an integer that serves as input to
     * hash functions.  This mapping is based on the idea of integer
     * multiplicative hashing, where we do multiplies for successive
     * characters of the key (adding in the position to distinguish
     * permutations of the key from each other).
     *
     * @param string to hash
     * @returns hashcode
     */
    int stringToHashCode(String key)
    {
    	int A = 1952786893;
	
    	int v = A;
    	for (int j = 0; j < key.length(); j++)
	    {
    		char c = key.charAt(j);
    		v = A * (v + (int) c + j) >> 16;
	    }
	
    	return v;
    }

    /**
     * Use this function to print out your table for debugging
     * purposes.
     */
    public String toString() 
    {
    	StringBuilder sb = new StringBuilder();
	
    	for(int i = 0; i < nBuckets; i++) 
	    {
    		sb.append(i+ "  ");
    		if (buckets[i] == null) 
		    {
    			sb.append("\n");
    			continue;
		    }
    		for (Record r : buckets[i]) 
		    {
    			sb.append(r.key + "  ");
		    }
    		sb.append("\n");
	    }
    	return sb.toString();
    }
}
