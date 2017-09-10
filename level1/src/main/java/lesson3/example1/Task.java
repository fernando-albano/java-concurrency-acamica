package lesson3.example1;

public class Task implements Runnable {

	private Factory factory;
	
	public Task(Factory factory) {
		this.factory = factory;
	}
	
	@Override
	public void run() {
		Object object = factory.getObject();
		System.out.println(Thread.currentThread().getName() + " got " + object);
		object = factory.getObject();
		System.out.println(Thread.currentThread().getName() + " got " + object);
	}
}
