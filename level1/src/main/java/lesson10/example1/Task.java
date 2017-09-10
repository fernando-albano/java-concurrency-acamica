package lesson10.example1;

public class Task implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(10000000); // wait 10000 seconds
		} catch (InterruptedException e) {
			System.out.println("Interrupted " + Thread.currentThread().getName());
		}
		System.out.println("Finished " + Thread.currentThread().getName());
	}
}
