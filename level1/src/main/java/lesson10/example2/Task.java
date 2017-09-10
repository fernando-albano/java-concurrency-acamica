package lesson10.example2;

public class Task implements Runnable {

	private Object object;
	
	public Task(Object object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		waitForMain();
		System.out.println("Finished: " + Thread.currentThread().getName());
	}
	
	private void waitForMain() {
		synchronized (object) {
			try {
				System.out.println(Thread.currentThread().getName() + "waiting for main thread");
				object.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
