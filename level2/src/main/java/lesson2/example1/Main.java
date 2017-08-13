package lesson2.example1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Example of ScheduledThreadPoolExecutor usage.
 */
public class Main {
	
	private static long WAIT = 10L;
	private static int TASKS = 5;

	public static void main(String[] args) {
		
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor)
			Executors.newScheduledThreadPool(5);

		System.out.println("Scheduling tasks..");
		MyTask task = new MyTask("2 seconds delayed.");
		executor.schedule(task, 2L, TimeUnit.SECONDS);
		
		task = new MyTask("4 seconds delayed, and once every 1 second.");
		executor.scheduleAtFixedRate(task, 4L, 1L, TimeUnit.SECONDS);
		
		task = new MyTask("6 seconds delayed, and once every 1 second after last execution finished.");
		executor.scheduleWithFixedDelay(task, 6L, 1L, TimeUnit.SECONDS);
		
		for (int i=0; i<TASKS; i++) {
			executor.execute(new MyTask("Task " + i));
		}
		
		sleep(WAIT, TimeUnit.SECONDS);
		executor.shutdown();
		System.out.println("Executor was terminated.");
	}

	private static void sleep(long time, TimeUnit unit) {
		try {
			unit.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}