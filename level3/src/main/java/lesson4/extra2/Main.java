package lesson4.extra2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Example of AtomicReferenceArray usage.
 */
public class Main {

    private static final int TASKS = 10;
    private static final int ACCOUNTS = 10;
    private static final long WAITING_TIME = 5;
    private static final long INITIAL_BALANCE = 1000000L;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(TASKS);
        AccountSet accounts = new AccountSet(ACCOUNTS, INITIAL_BALANCE);

        List<Future<Long>> results = new ArrayList<>(TASKS);
        for (int i=0; i<TASKS; i++) {
            results.add(executor.submit(new BankingTask(accounts)));
        }

        Long taskMovements = 0L;
        try {
            TimeUnit.SECONDS.sleep(WAITING_TIME);
            executor.shutdownNow();

            for (Future<Long> result : results) {
                taskMovements += result.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        long movements = 0L;
        for (int i=0; i<ACCOUNTS; i++) {
            Balance balance = accounts.getBalance(i);
            movements += balance.getMovements();
            System.out.println("Final balance for account " + i + " is " + balance.getBalance());
        }
        System.out.println("Total movements were " + movements + " and should be " + taskMovements);
    }
}
