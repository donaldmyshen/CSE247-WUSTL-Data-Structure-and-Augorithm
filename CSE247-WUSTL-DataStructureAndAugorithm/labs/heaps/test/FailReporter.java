package heaps.test;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class FailReporter extends TestWatcher {
		
	@Override
	public void failed(Throwable t, Description d) {
		System.out.println("===== Failure report for " + d + " ========");
		System.out.println("Failure message: " + t);
		System.out.println("===== End Failure report for " + d + " ========");
		System.out.println("\n");
	}

}
