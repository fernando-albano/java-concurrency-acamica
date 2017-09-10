package lesson10.example1;

/**
 * Example of thread interruption using ThreadGroup.
 */
public class Main {

	public static void main(String[] args) {

		ThreadGroup group = new ThreadGroup("Task Group");

		for (int i=0; i<5; i++) {
			Thread thread = new Thread(group, new Task(), "Thread " + i);
			thread.start();
		}

		group.interrupt();

		System.out.println("Finished " + Thread.currentThread().getName());
	}
}
