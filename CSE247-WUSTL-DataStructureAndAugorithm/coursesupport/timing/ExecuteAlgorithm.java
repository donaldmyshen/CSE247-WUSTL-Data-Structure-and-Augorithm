package timing;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import timing.output.Output;

/**
 * 
 * @author roncytron
 * 
 * Allows runs of an algorithm for a specific input size.
 * This is used for generating timing profiles for an algorithm.
 *
 * @param <T> Input type for the algorithm
 * @param <U> Output type for the algorithm
 */
public class ExecuteAlgorithm<T,U> {

	private final static int NUMREPEATS = 3;
	private U              results;
	private T              input;
	private Algorithm<T,U> algorithm;
	private Long           ticks;
	private Duration       time;

	/**
	 * 
	 * @param inputProvider source for the input to the algorithm
	 * @param algorithm     the algorithm itself
	 * @param size          describes the size of the input to be supplied by the inputProvider
	 */
	public ExecuteAlgorithm(InputProvider<T> inputProvider, Algorithm<T,U> algorithm, InputSpec size) {
		this.input     = inputProvider.genInput(size);
		this.algorithm = algorithm;
	}

	/**
	 * Load the input, and then run the algorithm under the
	 * controlled timing setting.
	 */
	public void run() {
		algorithm.loadInput(input);
		GenResults gs = new GenResults(algorithm, NUMREPEATS);
		gs.run();
		this.results = algorithm.getResults();
		this.ticks   = gs.getTicks();
		this.time    = gs.getTime();
	}

	public U getResults() {
		return results;
	}

	public Duration getTime() {
		return time;
	}

	public Long getTicks() {
		return ticks;
	}
	
	/**
	 * 
	 * @param name
	 * @param className String name of the class to be instantiated
	 * @param ip can provide suitable input of specified size
	 * @param sizes values of n to try for the algorithm
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public static<T,U> List<U> timeAlgorithm(
			String name,
			String className,
			InputProvider<T> ip,
			Iterable<Integer> sizes
			) {
		try {
			List<U> results = new LinkedList<U>();
			Algorithm<T,U> alg = (Algorithm<T,U>) Class.forName(className).newInstance();
			Output ticks = new Output(name+".ticks", name+"-ticks");
			Output times = new Output(name+".time", name+"-time");
			for (int size : sizes) {
				ExecuteAlgorithm<T,U> ea = new ExecuteAlgorithm<T,U>(
						ip, alg, InputSpec.gen(size)
						);
				ea.run();
				ticks.writeSizeValue(size, ea.getTicks());
				times.writeSizeValue(size, ea.getTime().toMillis());
				System.out.println("size \tticks \ttime");
				System.out.println(size+" \t"+ea.getTicks()+" \t"+ea.getTime().toMillis());
				results.add(ea.getResults());
			}
			return results;
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Error("Error " + t);
		}

	}

}
