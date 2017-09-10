package lesson8.example1;

public class Task implements Runnable {

	private final Object resource1, resource2;
	
	public Task(Object resource1, Object resource2) {
		this.resource1 = resource1;
		this.resource2 = resource2;
	}
	
	@Override
	public void run() {
		try {
			synchronized(resource1) {
				Thread.sleep(100);
				synchronized(resource2) {
					System.out.println(Thread.currentThread().getName() + " avoided deadlock");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
