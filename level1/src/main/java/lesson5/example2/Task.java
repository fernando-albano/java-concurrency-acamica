package lesson5.example2;

public class Task implements Runnable {

	private UnsafeCounter counter;
	private int times;
	
	public Task(UnsafeCounter counter, int times) {
		this.counter = counter;
		this.times = times;
	}
	
	@Override
	public void run() {
		for (int i=0; i<times; i++) {
			counter.increment();
		}
	}
}
