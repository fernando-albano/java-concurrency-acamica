package lesson5.example1;

public class SafeLazyInit {

	private Object instance = null;
	
	public synchronized Object getInstance() {
		if (instance == null) {
			instance = new Object();
		}
		return instance;
	}
}
