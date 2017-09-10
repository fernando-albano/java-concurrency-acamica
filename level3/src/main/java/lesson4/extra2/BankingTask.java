package lesson4.extra2;

import java.util.Random;
import java.util.concurrent.Callable;

public class BankingTask implements Callable<Long> {

    private AccountSet accounts;

    public BankingTask(AccountSet accounts) {
        this.accounts = accounts;
    }

    @Override
    public Long call() {
        Random random = new Random();
        long movements = 0;
        int accountsSize = accounts.size();

        while (!Thread.interrupted()) {
            long amount = random.nextInt(10) * 10000;
            int accountId = random.nextInt(accountsSize);

            if (random.nextBoolean()) {
                accounts.add(accountId, amount);
            } else {
                accounts.subtract(accountId, amount);
            }
            movements++;
        }
        return movements;
    }
}
