package lesson4.extra1;

import java.util.Random;
import java.util.concurrent.Callable;

public class BankingTask implements Callable<Long> {

    private int id;
    private Account account;

    public BankingTask(int id, Account account) {
        this.id = id;
        this.account = account;
    }

    @Override
    public Long call() {
        Random random = new Random();
        long movements = 0;

        while (!Thread.interrupted() && account.areMovementsAllowed()) {
            long amount = random.nextInt(10) * 10000;

            if (random.nextBoolean()) {
                account.add(id, amount);
            } else {
                account.subtract(id, amount);
            }
            movements++;
        }

        System.out.println(Thread.currentThread().getName() + " performed " + movements + " movements.");
        return account.getTaskBalance(id);
    }
}
