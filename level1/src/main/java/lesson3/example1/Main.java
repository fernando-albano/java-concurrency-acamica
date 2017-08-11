package lesson3.example1;

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