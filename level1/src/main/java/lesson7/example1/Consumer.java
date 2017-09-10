package lesson7.example1;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {
	
	private Queue<String> produced;
	private Semaphore mutex, producedSemaphore;
	
	public Consumer(Queue<String> produced, Semaphore mutex, Semaphore producedSemaphore) {
		this.produced = produced;
		this.mutex = mutex;
		this.producedSemaphore = producedSemaphore;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				producedSemaphore.acquire();
				mutex.acquire();
				String product = produced.poll();
				System.out.println(Thread.currentThread().getName() + " got: " + product);
				mutex.release();
			}
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " was interrupted.");
		}
	}
}
