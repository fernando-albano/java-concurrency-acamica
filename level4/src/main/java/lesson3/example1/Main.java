package lesson3.example1;

import java.util.concurrent.*;

/**
 * Example of PriorityBlockingQueue usage.
 */
public class Main {

	private static final int CONSUMERS = 5;
	private static final int QUEUE_SIZE = 30;

	public static void main(String[] args) {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(CONSUMERS);
		BlockingQueue<Message> queue = new PriorityBlockingQueue<>(QUEUE_SIZE, new Priority());

		for (int i=0; i<CONSUMERS; i++) {
			executor.execute(new Task(queue));
		}

		try {
			for (int i=1; i<=QUEUE_SIZE; i++) {
				queue.put(new Message("MESSAGE " + i, (i % 3) + 1));
			}

			TimeUnit.SECONDS.sleep(2);
			executor.shutdownNow();
			executor.awaitTermination(2, TimeUnit.SECONDS);

		} catch (InterruptedException e) {
			System.out.println("Main thread was interrupted.");
		}
		System.out.println("Queue size: " + queue.size());
	}
}
