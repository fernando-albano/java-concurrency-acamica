package lesson2.example1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Example of CountDownLatch usage.
 */
public class Main {

	private static final int TASKS = 5;
	private static final int NUMBERS = 5000;

	public static void main(String[] args) {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(TASKS);
		AtomicIntegerArray partialSums = new AtomicIntegerArray(TASKS);
		CountDownLatch latch = new CountDownLatch(TASKS);
		Random random = new Random();

		for (int i=0; i<TASKS; i++) {
			int[] numbers = random.ints(NUMBERS, 0, 10).toArray();
			executor.execute(new Task(i, numbers, latch, partialSums));
		}

		int sum = 0;
		try {
			latch.await(5, TimeUnit.SECONDS);

			for (int i=0; i<TASKS; i++) {
				sum += partialSums.get(i);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread was interrupted.");
		}
		executor.shutdown();
		System.out.println("Total sum is: " + sum);
	}
}
