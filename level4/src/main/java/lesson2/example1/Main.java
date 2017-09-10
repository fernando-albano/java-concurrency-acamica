package lesson2.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * SynchronizedMap vs ConcurrentHashMap.
 */
public class Main {

	private static final int EMPLOYEES_TO_CHECK_PER_TASK = 100;
	private static String[] DEPARTMENTS = { "Administration", "Logistics",
			"Marketing", "Service Desk", "Legal" };

	public static void main(String[] args) {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		EmployeesCacheSync cache = new EmployeesCacheSync(executor, DEPARTMENTS);

		long time = System.nanoTime();
		List<Future<Integer>> results = new ArrayList<>(DEPARTMENTS.length);
		for (int i=0; i<DEPARTMENTS.length; i++) {
			results.add(executor.submit(
					new Task(EMPLOYEES_TO_CHECK_PER_TASK, DEPARTMENTS[i], cache)));
		}

		int totalChecks = 0;
		try {
			for (Future<Integer> result : results) {
				totalChecks += result.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		time = (System.nanoTime() - time ) / 1000000;
		System.out.println("Total time elapsed: " + time + " ms");
		System.out.println("Total matches for tasks: " + totalChecks);
		System.out.println("Total cached employees: " + cache.size());
		executor.shutdown();
	}
}
