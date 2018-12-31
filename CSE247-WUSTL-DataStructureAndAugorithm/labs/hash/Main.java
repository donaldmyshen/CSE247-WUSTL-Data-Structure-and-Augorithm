package hash;

//
// MAIN.JAVA
// Sequence matching driver for CSE 247 Hashing lab
//
// SYNTAX: java Main <match length> <corpus file> <pattern file> 
//                   [ <mask file> ]
//
// This sequence matching program reads two sequences, a CORPUS and a
// PATTERN, from files on disk and finds all strings of a given match
// length M that occur in both corpus and pattern.  Matching
// substrings are printed in the form 
//      <index 1> <index 2> <substring>
// where the two indices are the offsets of the match within the
// corpus and pattern, respectively, and <substring> is the actual
// matching string.
//
// As an optional fourth argument, the program can take a file
// containing a MASK sequence.  Substrings of the mask sequence are
// considered "uninteresting" and so must not be reported by the
// matching code.  To implement this requirement, we delete any
// occurrences of substrings in the mask sequence from our pattern
// table before performing the search.
//
// WARNING: ANY CHANGES YOU MAKE TO THIS FILE WILL BE DISCARDED
// BY THE AUTO-GRADER!  Make sure your code works with the
// unmodified original driver before you turn it in.
//

public class Main {
    
    public static void main(String args[])
    {
	int matchLength   = 0;
	String corpusSeq  = null;
	String patternSeq = null;
	String maskSeq    = null;
	
	if (args.length < 3)
	    {
		System.out.println("Syntax: Main <match length> " +
				   "<corpus file> <pattern file> " +
				   "[ <mask file> ]");
		return;
	    }
	else
	    {
		matchLength = Integer.parseInt(args[0]);
		corpusSeq   = SeqReader.readSeq(args[1]);
		patternSeq  = SeqReader.readSeq(args[2]);
		
		System.out.println("M = " + matchLength);
		System.out.println("CORPUS: " + corpusSeq.length() + " bases");
		System.out.println("PATTERN: " + patternSeq.length()+" bases");
		
		if (args.length > 3)
	 	    {
			maskSeq = SeqReader.readSeq(args[3]);
			System.out.println("MASK: "+maskSeq.length()+" bases");
		    }
	    }
	
	StringTable table = createTable(patternSeq, matchLength);
	
	if (maskSeq != null)
	    maskTable(table, maskSeq, matchLength);
	
	findMatches(table, corpusSeq, matchLength);
    }
    
    
    // Create a new StringTable containing all substrings of the pattern
    // sequence.
    // 
    public static StringTable createTable(String patternSeq, int matchLength)
    {
	// proposed table size: next power of 2 >= pattern seq length
	int tableSize = 
	    (int) Math.pow(2, 
			   32 - Integer.numberOfLeadingZeros(patternSeq.length()/10));
	// limit table size for large sequences
	tableSize = Math.min(tableSize, 65536);
	
	StringTable table = new StringTable(tableSize);
	
	for (int j = 0; j < patternSeq.length() - matchLength + 1; j++)
	    {
		String key = patternSeq.substring(j, j + matchLength);
		
		Record rec = table.find(key);
		
		if (rec == null) // not found -- need new Record
		    {
			rec = new Record(key);
			if (!table.insert(rec))
			    System.out.println("Error (insert): hash table is full!\n");
		    }
		
		rec.positions.add(new Integer(j));
	    }
	
	return table;
    }
    
    
    // Remove all substrings in the mask sequence from a StringTable.
    // 
    public static void maskTable(StringTable table, String maskSeq,
				 int matchLength)
    {
	for (int j = 0; j < maskSeq.length() - matchLength + 1; j++)
	    {
		String key = maskSeq.substring(j, j + matchLength);
		
		table.remove(key);
	    }
    }
    
    
    // Find and print all matches between the corpus sequence and any
    // string in a StringTable.
    //
    public static void findMatches(StringTable table, String corpusSeq,
				   int matchLength)
    {
	for (int j = 0; j < corpusSeq.length() - matchLength + 1; j++)
	    {
		String key = corpusSeq.substring(j, j + matchLength);
		
		Record rec = table.find(key);
		if (rec != null)
		    {
			for (int k = 0; k < rec.positions.size(); k++)
			    {
				System.out.println(j + " " + 
						   rec.positions.get(k) +" "+ 
						   key);
			    }
		    }
	    }
    }
}
