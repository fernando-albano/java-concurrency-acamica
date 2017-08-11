package lesson2.example1;

import java.time.LocalTime;

public class MyTask implements Runnable {
		
	private String name;
	
	public MyTask(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(LocalTime.now() + ": " +
			Thread.currentThread().getName() + " is executing " + name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
