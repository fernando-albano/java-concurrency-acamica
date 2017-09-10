package lesson5.example2;

public class SafeCounter {

	private int count = 0;
	
	public synchronized void increment() {
		count++;
	}
	
	public int getCount() {
		return count;
	}
}
