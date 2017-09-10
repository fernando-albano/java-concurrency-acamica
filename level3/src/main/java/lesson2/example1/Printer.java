package lesson2.example1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

	private final Lock lock = new ReentrantLock(false);
	private int completedJobs = 0;
	
	public void print(String message) {
		lock.lock();
		try {
			System.out.println(message + " - Printed " + ++completedJobs + " jobs.");
		} finally {
			lock.unlock();
		}
	}
	
	public int getCompletedJobs() {
		return completedJobs;
	}
}
