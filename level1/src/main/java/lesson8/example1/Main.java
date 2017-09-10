package lesson8.example1;

/**
 * Example of deadlock occurrence.
 */
public class Main {

	// Works fine
	/*public static void main(String[] args) {
		Object resource1 = new Object(), resource2 = new Object();
		new Thread(new Task(resource1, resource2)).start();
		new Thread(new Task(resource1, resource2)).start();
	}*/

	// Produces deadlock
	public static void main(String[] args) {
		Object resource1 = new Object(), resource2 = new Object();
		new Thread(new Task(resource1, resource2)).start();
		new Thread(new Task(resource2, resource1)).start();
	}
}
