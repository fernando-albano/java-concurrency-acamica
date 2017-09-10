package lesson5.example1;

public class LazyInit {

	private Object instance = null;
	
	public Object getInstance() {
		if (instance == null) {
			instance = new Object();
		}
		return instance;
	}
}
