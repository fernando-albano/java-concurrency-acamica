package lesson7.example1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Example of Producer/Consumer model using Semaphores.
 */
public class Main {

	private static Semaphore producedSemaphore = new Semaphore(0);
	private static Semaphore mutex = new Semaphore(1);
	private static Queue<String> produced = new LinkedList<>();

	public static void main(String[] args) {

		Thread producer = new Thread(new Producer(15, produced, mutex, producedSemaphore));
		Thread consumer1 = new Thread(new Consumer(produced, mutex, producedSemaphore));
		Thread consumer2 = new Thread(new Consumer(produced, mutex, producedSemaphore));

		producer.start();
		consumer1.start();
		consumer2.start();

		try {
			producer.join();
			while(!isProducedQueueEmpty()) {
				Thread.sleep(500);
			}
			consumer1.interrupt();
			consumer2.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static boolean isProducedQueueEmpty() throws InterruptedException {
		mutex.acquire();
		boolean isEmpty = produced.isEmpty();
		mutex.release();
		return isEmpty;
	}
}
