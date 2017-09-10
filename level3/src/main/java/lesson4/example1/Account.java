package lesson4.example1;

import java.util.concurrent.atomic.AtomicLong;

public class Account {

	private AtomicLong balance;
	
	public Account(Long balance) {
		this.balance = new AtomicLong(balance);
	}
	
	public Long getBalance() {
		return balance.get();
	}
	
	public Long add(long amount) {
		return balance.addAndGet(amount);
	}
	
	public Long subtract(long amount) {
		return balance.addAndGet(amount * -1);
	}
}
