package lesson3.example1;

import java.util.concurrent.BlockingQueue;

public class Task implements Runnable {

	private BlockingQueue<Message> queue;
	
	public Task(BlockingQueue<Message> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Message message = queue.take();
				System.out.println("Received " + message.getContent() + 
					" with priority " + message.getPriorityName());
				Thread.sleep(3);
			}
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + 
				" consumer was interrupted.");
		}
	}
}
