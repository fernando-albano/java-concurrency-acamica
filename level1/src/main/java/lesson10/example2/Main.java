package lesson10.example2;

/**
 * Example of wait/notify methods usage.
 */
public class Main {

	public static void main(String[] args) {

		Object object = new Object();		
		new Thread(new Task(object)).start();
		new Thread(new Task(object)).start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (object) {
			System.out.println(Thread.currentThread().getName() + " woke up from sleep, notifying the other threads..");
			object.notifyAll();
		}
		System.out.println("Finished: " + Thread.currentThread().getName());
	}	
}
