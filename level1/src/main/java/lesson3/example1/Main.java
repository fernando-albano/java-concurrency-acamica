package lesson3.example1;

/**
 * Example of ThreadLocal usage.
 */
public class Main {

	public static void main(String[] args) {

		Factory factory = new Factory();
		Thread[] threads = new Thread[4];
		
		for (int i=0; i<threads.length; i++) {
			threads[i] = new Thread(new Task(factory));
			threads[i].start();
		}
	}
}
