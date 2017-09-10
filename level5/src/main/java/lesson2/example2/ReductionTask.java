package lesson2.example2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ReductionTask implements Runnable {
	
	private AtomicIntegerArray partialSums;
	
	public ReductionTask(AtomicIntegerArray partialSums) {
		this.partialSums = partialSums;
	}
	
	@Override
	public void run() {
		int sum = 0;
		for (int i=0; i<partialSums.length(); i++) {
			sum += partialSums.get(i);
		}
		
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Total sum is: " + sum);
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName() + 
				" was interrupted when performing reduction.");
		}
	}
}
