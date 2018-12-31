package timing;

/**
 * Provides an input of the specified complexity.
 * @author roncytron
 *
 * @param <T>
 */
public interface InputProvider<T> {
	
	/**
	 * 
	 * @param c the complexity parameter 
	 * @return
	 */
	public T genInput(InputSpec c);

}
