package timing;

/**
 * 
 * @author roncytron
 *
 * @param <T> the type of the input for this algorithm
 * @param <U> the type of the output for this algorithm
 */
public interface Algorithm<T,U> extends RepeatRunnable {
	
	public void loadInput(T input);
	public U    getResults();

}
