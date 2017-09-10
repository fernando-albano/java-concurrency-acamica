package lesson4.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class BankingTask implements Callable<Long> {

	private Account account;
	private List<Long> movements = new ArrayList<>();

	public BankingTask(Account account) {
		this.account = account;
	}
	
	@Override
	public Long call() {
		Random random = new Random();

		while (!Thread.interrupted()) {
			long amount = random.nextInt(10) * 10000;

			if (random.nextBoolean()) {
				account.add(amount);
				movements.add(amount);
			} else {
				account.subtract(amount);
				movements.add(-1 * amount);
			}
		}
		
		System.out.println(Thread.currentThread().getName() + 
			" performed " + movements.size() + " operations.");
		return movements.stream().reduce(0L, Long::sum);
	}
}
