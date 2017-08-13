package lesson2.example2;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Example of Executor interruption.
 */
public class Main {

	private static final long WAITING_TIME = 5L;
	private static final long N = 500L;
	private static final int TASKS = 5;

	public static void main(String[] args) {
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(TASKS);
		for (int i=0; i<TASKS; i++) {
			executor.execute(new Task(N));
		}
		
		System.out.println("Starting executor shutdown..");
		executor.shutdown();

		try {
			if (!executor.awaitTermination(WAITING_TIME, TimeUnit.SECONDS)) {
				System.out.printf("Executor was not terminated. Active " + 
					"tasks: %s.\n", executor.getActiveCount());
				System.out.println("Terminating executor now..");
				executor.shutdownNow();
				executor.awaitTermination(WAITING_TIME, TimeUnit.SECONDS);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread was interrupted.");
			e.printStackTrace();
		} finally {
			if (!executor.isTerminated()) {
				System.out.printf("Executor was not terminated. Active tasks: %s.\n", 
					executor.getActiveCount());
				System.out.println("Terminating application..");
				System.exit(1);
			}
		}
		System.out.println("Executor was successfully terminated.");
	}
}
