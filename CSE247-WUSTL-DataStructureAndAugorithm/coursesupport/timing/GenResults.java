package timing;

import java.time.Duration;
import java.util.PriorityQueue;

import timing.results.LeastChooser;
import timing.results.ResultsChooser;

/**
 * Obtains results from a RepeatRunnable by running it repeats times
 * and then choosing the results using the specified ResultsChoosers.
 * @author roncytron
 *
 */
public class GenResults implements Runnable {

	final private RepeatRunnable rr;
	private int repeats;
	private Duration time;
	private Long     ticks;
	private ResultsChooser<Duration> timeChooser;
	private ResultsChooser<Long>     ticksChooser;



	/**
	 * 
	 * @param rr  The payload to be run 
	 * @param repeats How many times to try the payload for timing
	 * @param timeChooser How to choose the results for time
	 * @param ticksChooser How to choose the results for ticks
	 */
	public GenResults(RepeatRunnable rr, int repeats, ResultsChooser<Duration> timeChooser, ResultsChooser<Long> ticksChooser) {
		this.rr      = rr;
		this.repeats = repeats;
		this.timeChooser  = timeChooser;
		this.ticksChooser = ticksChooser;
		this.time    = null;
		this.ticks   = null;
	}
	
	public GenResults(RepeatRunnable rr, int repeats) {
		this(rr, repeats, 
				new LeastChooser<Duration>(), 
				new LeastChooser<Long>()
				);
	}

	public Duration getTime() {
		return this.time;
	}

	public Long getTicks() {
		return this.ticks;
	}

	/**
	 * Actually do the experiment.  We run it repeats times, and then
	 * use the ResultsChooser to pick from the results.
	 */
	public void run() {
		PriorityQueue<Duration> pq = new PriorityQueue<Duration>();
		PriorityQueue<Long>     tq = new PriorityQueue<Long>();
		for (int i=0; i < repeats; ++i) {
			TimedRunnable tr = new TimedRunnable(rr);
			tr.start();
			Duration time = tr.getTime();
			Long ticks = tr.getTicker().getTickCount();
			tq.offer(ticks);
			pq.offer(time);
		}
		
		this.time  = timeChooser.getValue(pq);
		this.ticks = ticksChooser.getValue(tq);
	}


}
