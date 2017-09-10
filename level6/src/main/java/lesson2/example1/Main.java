package lesson2.example1;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Example of Merge-Sort using ForkJoinPool.
 */
public class Main {

	private static final int ELEMENTS = 10000;
	private static final int MAX_ELEMENTS_TO_PROCESS_PER_TASK = 10000;

	public static void main(String[] args) {

		Random random = new Random();
		int[] ints = random.ints(ELEMENTS).toArray();

		ForkJoinPool pool = new ForkJoinPool();
		SortTask task = new SortTask(MAX_ELEMENTS_TO_PROCESS_PER_TASK, 0, ints.length - 1, ints);

		long nanos = System.nanoTime();
		pool.execute(task);

		try {
			while (!task.isDone()) {
				Thread.sleep(5);
				printPoolStatus(pool);
			}

			int[] ordered = task.get();
			nanos = (System.nanoTime() - nanos) / 1000000;

			System.out.println("First of " + ordered.length + " is " + ordered[0]);
			System.out.println("Last of " + ordered.length + " is " +
					ordered[ordered.length - 1]);

		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Processing was interrupted.");
			e.printStackTrace();
		}

		System.out.println("Total tasks used: " + SortTask.TASK_COUNT.get());
		System.out.println("Total time: " + nanos + " ms.");
	}

	public static void printPoolStatus(ForkJoinPool pool) {
		System.out.printf("Parallelism: %d - Pool size: %d - " +
						"Active threads: %d - Task Count: %d - Steal Count: %d.\n",
				pool.getParallelism(), pool.getPoolSize(), pool.getActiveThreadCount(),
				pool.getQueuedTaskCount(), pool.getStealCount());
	}
}
