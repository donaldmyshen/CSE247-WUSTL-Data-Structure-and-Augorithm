package timing.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.csvreader.CsvWriter;

import timing.utils.SizeAndLong;

public class Output {

	private CsvWriter w;

	public Output(String exper, String file) {
		this(exper,file, false);
	}

	private static int findDigit(String p, String q) {
		for (int i=0; i < 100; ++i) {
			File f = new File(p+i+"."+q);
			if (!f.exists()) {
				return i;
			}
		}
		throw new Error("Out of digits for unique file " + p);
	}

	public Output(String exper, String file, boolean append) {
		if (!file.endsWith(".csv")) {
			file    = file + ".csv";
		}
		try {
			String[] parts = file.split("\\.(?=[^\\.]+$)");
			int digit = findDigit("outputs/"+parts[0], parts[1]);
			FileWriter fw = new FileWriter("outputs/" + parts[0]+digit+"."+parts[1], append);
			this.w  = new CsvWriter(fw, ',');
			w.write("n");
			w.write(exper);
			w.endRecord();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Write a series of sizes and times to an output file
	 * @param file filename to which the output should be appended
	 * @param exper column name
	 * @param iter iterator that returns the sizes and durations
	 */
	public void writeSizeTiming(Iterable<SizeAndLong> iter) {
		for (SizeAndLong st : iter) {
			writeSizeValue(st.size, st.value);
		}
	}

	public void writeSizeValue(int size, long value) {
		try {
			// System.out.println("writing " + size + " " + value);
			w.write("" + size);
			w.write("" + value);
			w.endRecord();
			w.flush();
		} catch (Throwable t) {
			throw new Error("oops");
		}

	}

	public void close() {
		w.close();
	}

}
