package timing.utils;

/**
 * This class wraps some calls that throw checked exceptions.
 * Those exceptions cannot happen in most reasonable settings.
 * Here's why:  one thread can .interrupt() another, so that if
 *   that other thread is sleeping or waiting, it experiences the
 *   InterruptedException.  However, if that thread is busy doing
 *   something, and not in a wait or sleep state, then interrupting
 *   that thread does no good whatsoever.  So we can't count on
 *   .interrupt() to really stop a Thread at all.
 * Thus, for our purposes, we'll wrap those calls so the code
 * is cleaner throughout the project.
 * And, for the reasons I state above,
 * I believe InterruptedException should have been an unchecked exception.
 * 
 * @author roncytron
 *
 */
public class Wrappers {

	/**
	 * Instead of saying Thread.sleep(millis)
	 *    say  Wrappers.sleep(millis)
	 *    This code handles the exception that never occurs
	 *    so your code will look cleaner
	 * @param secs
	 */

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			impossible(e);
		}
	}

	/**
	 * Instead of saying (this).wait() or o.wait
	 *    say  Wrappers.wait(this) or Wrappers.wait(o).
	 *    This code handles the exception so your
	 *    code can look cleaner.  This code does ensure the
	 *    Thread has a lock on o before waiting, so there is not
	 *    a need to synchronize on o prior to calling this method,
	 *    BUT for atomicity reasons you may need that lock anyway.
	 * @param o
	 */
	public static void wait(Object o) {
		try {
			synchronized(o) {
				o.wait();
			}
		} catch (InterruptedException e) {
			impossible(e);
		}
	}
	
	/**
	 * We really don't need to wrap notifyAll because
	 *   it can't throw an Exception.
	 * But for symmetry in our code, we do provide the following
	 *   wrapped version.  
	 * Like wait(Object o), the method below does obtain a
	 *   lock on o, which is necessary, but for atomicity, a given application
	 *   may require holding the lock on o both before and after
	 *   the call to notifyAll(Object o).
	 * @param o
	 */
	public static void notifyAll(Object o) {
		synchronized(o) {
			o.notifyAll();
		}
	}

	public static void impossible(Exception e) {
		throw new Error("Inconceivable!  The impossible has happened " + e);
	}
}
