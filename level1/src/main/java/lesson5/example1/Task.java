package lesson5.example1;

public class Task implements Runnable {

	private LazyInit lazyInit;
	
	public Task(LazyInit lazyInit) {
		this.lazyInit = lazyInit;
	}
	
	@Override
	public void run() {
		Object object = lazyInit.getInstance();
		System.out.println("Got: " + object);
	}
}
