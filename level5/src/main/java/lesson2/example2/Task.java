package lesson2.example2;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Task implements Runnable {

	private int id;
	private int[] numbers;
	private CyclicBarrier barrier;
	private AtomicIntegerArray partialSums;
	
	public Task(int id, int[] numbers, CyclicBarrier barrier, AtomicIntegerArray partialSums) {
		this.id = id;
		this.numbers = numbers;
		this.barrier = barrier;
		this.partialSums = partialSums;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				int sum = Arrays.stream(numbers).reduce(Integer::sum).getAsInt();
				partialSums.set(id, sum);
				barrier.await();
			}
		} catch (InterruptedException | BrokenBarrierException e) {
			if (id == 0 && (barrier.isBroken())) {
				System.out.println("The barrier is broken.");
			}
			System.out.println(Thread.currentThread().getName() + " was interrupted while waiting.");
		}
	}
}
