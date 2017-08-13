package lesson3.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Example of Callable and Future usage.
 */
public class Main {

	private static final long N = 5000L;
	private static final int TASKS = 5;

	public static void main(String[] args) {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(TASKS);
		List<Future<Long>> results = new ArrayList<>(TASKS);

		for (int i=0; i<TASKS; i++) {
			Callable<Long> task = new Task(N);
			Future<Long> result = executor.submit(task);
			results.add(result);
		}

		for (Future<Long> result : results) {
			try {
				System.out.println("Obtained result: " + result.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		executor.shutdown();
		System.out.println("Executor was successfully terminated.");
	}
}

