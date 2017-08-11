package lesson3.example1;

import java.util.concurrent.Callable;

public class Task implements Callable<Long> {

	private Long num;
	
	public Task(long num) {
		this.num = num;
	}
	
	@Override
	public Long call() {
		try {
			return fibonacci(num);
		} catch(InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " was interrupted.");
			return 0L;
		}
	}

	public long fibonacci(long num) throws InterruptedException {
		if (num == 0 || num == 1) {
			return num;
		}
		long fibonacci = 0;
		long fibonacci_1 = 1, fibonacci_2 = 0;
		for (int i=2; i<=num && !Thread.interrupted(); i++) {
			fibonacci = fibonacci_1 + fibonacci_2;
			fibonacci_2 = fibonacci_1;
			fibonacci_1 = fibonacci;
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
		return fibonacci;
	}
}
