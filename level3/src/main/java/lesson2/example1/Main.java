package lesson2.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Example of ReentrantLock usage.
 */
public class Main {

	private static final int TASKS = 5;

	public static void main(String[] args) {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(TASKS);
		Printer printer = new Printer();
		
		List<Callable<Integer>> tasks = new ArrayList<>(TASKS);
		for (int i=0; i<TASKS; i++) {
			tasks.add(new Task(i, printer));
		}

		try {
			executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
		System.out.println("Printed " + printer.getCompletedJobs() + " jobs.");
	}
}