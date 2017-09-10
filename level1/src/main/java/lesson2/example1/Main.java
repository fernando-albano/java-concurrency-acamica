package lesson2.example1;

/**
 * Example of Thread interruption.
 */
public class Main {

	public static void main(String[] args) {

		Thread[] threads = new Thread[5];
		for (int i=0; i<threads.length; i++) {
			threads[i] = new Thread(new Task(), "Thread " + i);
			threads[i].start();
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getName());
	}
}
