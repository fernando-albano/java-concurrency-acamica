package lesson5.example1;

/**
 * Example of thread safe/unsafe.
 */
public class Main {

	public static void main(String[] args) {

		LazyInit lazyInit = new LazyInit();
		Thread[] threads = new Thread[10];

		for (int i=0; i<threads.length; i++) {
			threads[i] = new Thread(new Task(lazyInit));
			threads[i].start();
		}
	}
}