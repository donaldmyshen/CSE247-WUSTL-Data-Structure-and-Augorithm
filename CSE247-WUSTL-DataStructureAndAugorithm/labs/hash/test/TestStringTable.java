package hash.test;

import java.io.*;

import org.junit.Test;

import hash.Record;
import hash.StringTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestStringTable {

	private String [] testStrings = {
			"abcdefg",
			"bcdefgh",
			"cdefghix",
			"defghij",
			"efghijkxx",
			"fghijkl",
			"ghijklm",
			"hijklmnxxx",
			"ijklmno",
			"jklmnop",
			"klmnopqxxxx",
			"mnopqrs",
			"nopqrst",
			"opqrstuxxxxx",
			"pqrstuv",
			"qrstuvwxxxxxx"
	};

	private String [] missingStrings = {
			"foo",
			"bar",
			"baz",
			"quux"
	};

	@Test
	public void testInit() 
	{
		StringTable table = new StringTable(4);
		assertEquals("The table's size after initialization should be 0",
				table.size, 0);
	}

	@Test
	public void testInsert() 
	{
		StringTable table = new StringTable(4);
		int size = 0;
		for (int j = 0; j < testStrings.length; j++)
		{
			Record r = new Record(testStrings[j]);
			assertTrue("Could not insert record with key " 
					+  testStrings[j] + " into table",
					table.insert(r));
			assertEquals("Cannot find just-inserted record with key "
					+ testStrings[j] + " in table",
					r, table.find(testStrings[j]));
			size++;
			assertEquals("Table size does not match the anticipated size",
					size, table.size);
		}

		for (int j = 0; j < testStrings.length; j++)
		{
			Record r = new Record(testStrings[j]);
			assertFalse("Insertion of record with duplicate string "
					+  testStrings[j] + " should return false",
					table.insert(r));
		}	
	}	

	@Test
	public void testFind()
	{	
		StringTable table = new StringTable(4);
		for (int j = 0; j < testStrings.length; j++)
		{	
			Record r = new Record(testStrings[j]);
			table.insert(r);
		}	

		for (int j = 0; j < testStrings.length; j++)
		{
			Record r = table.find(testStrings[j]);
			assertTrue("Could not find previously inserted record with string"
					+ testStrings[j],
					r != null);

			assertEquals("Returned record from find has key "
					+  r.key + ", which does not match input "
					+ testStrings[j],
					r.key, testStrings[j]);

		}

		for (int j = 0; j < missingStrings.length; j++)
		{
			Record r = table.find(missingStrings[j]);
			assertTrue("Find of string " + missingStrings[j] 
					+ "not in table should return null!",
					r == null);
		}
	}


	@Test
	public void testDelete() 
	{
		StringTable table = new StringTable(4);
		int size = 0;

		for (int j = 0; j < testStrings.length; j++)
		{
			Record r = new Record(testStrings[j]);
			table.insert(r);
			size++;
		}

		for (int j = 0; j < testStrings.length/2; j++)
		{
			table.remove(testStrings[j]);
			size--;
		}

		assertEquals("Table size after deletions should be " + size,
				table.size, size);

		for (int j = 0; j < testStrings.length/2; j++)
		{	
			Record r = table.find(testStrings[j]);
			assertTrue("String " + testStrings[j] 
					+ " should no longer be in table!",
					r == null);
		}

		for (int j = testStrings.length/2; j < testStrings.length; j++)
		{
			Record r = table.find(testStrings[j]);

			assertTrue("Could not find previously inserted record with string"
					+ testStrings[j],
					r != null);

			assertEquals("Returned record from find has key "
					+  r.key + ", which does not match input "
					+ testStrings[j],
					r.key, testStrings[j]);
		}
	}

	private final String[] testCases = 
		{
				"4 labs/hash/test-cases/case1-corpus.txt labs/hash/test-cases/case1-pattern.txt labs/hash/test-cases/case1-mask.txt",
				"16 labs/hash/test-cases/case2-corpus.txt labs/hash/test-cases/case2-pattern.txt labs/hash/test-cases/case2-mask.txt",
				"18 labs/hash/test-cases/case3-corpus.txt labs/hash/test-cases/case3-pattern.txt",
				"180 labs/hash/test-cases/case4-corpus.txt labs/hash/test-cases/case4-pattern.txt labs/hash/test-cases/case4-mask.txt",
		};

	@Test
	public void testDNA1()
	{
		testWithDNA(1, testCases[0]);
	}

	@Test
	public void testDNA2()
	{
		testWithDNA(2, testCases[1]);
	}

	@Test
	public void testDNA3()
	{
		testWithDNA(3, testCases[2]);
	}

	@Test
	public void testDNA4()
	{
		testWithDNA(4, testCases[3]);
	}

	private void testWithDNA(int id, String argstring) 
	{
		PrintStream stdout = System.out;
		System.setOut(stdout);
		System.out.println("Testing DNA test case " + id);
		try {
			System.setOut(new PrintStream(new FileOutputStream("labs/hash/output/case"
					+ id + "-observed.txt")));
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		}

		String[] testInfo = argstring.split(" ");
		hash.Main.main(testInfo);

		// reset out to print on the console window
		System.setOut(stdout);

		// now need to compare outputs
		try {
			boolean testCase = compareOutput("labs/hash/output/case" 
					+ id +"-observed.txt", 
					"labs/hash/output/case"
							+ id +"-expected.txt");
			assertTrue("The observed output for case" 
					+ id +" differs from the expected output.",
					testCase);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Observed output for case" 
				+ id +" matches expected output!\n");
	}

	private boolean compareOutput(String observed, String expected) 
			throws IOException {
		BufferedReader reader1 = new BufferedReader(new FileReader(observed));
		BufferedReader reader2 = new BufferedReader(new FileReader(expected));

		String line1 = reader1.readLine();
		String line2 = reader2.readLine();

		boolean areEqual = true;
		int lineNum = 1;

		while(line1 != null || line2 != null) {
			if(line1==null || line2==null) {
				areEqual = false;
				break;	// extra lines in one of them
			}
			if(!line1.equals(line2)) {
				areEqual = false;
				break;
			}
			line1 = reader1.readLine();
			line2 = reader2.readLine();
			lineNum++;
		}
		reader1.close();
		reader2.close();
		if(!areEqual) {

			if(line1==null || line1.trim().equals("")) {
				System.out.println("The observed output is missing lines starting at line " + lineNum + "\n\texpected: "+line2);
			}
			else if(line2==null || line2.trim().equals("")) {
				System.out.println("The observed output has extra lines starting at line " + lineNum + "\n\tobserved: "+line1);
			}
			else{
				System.out.println("The output files do not match starting at line " + lineNum + " \n\tobserved: " +line1 + " \n\texpected: "+line2);
			}
		}
		return areEqual;
	}
}
