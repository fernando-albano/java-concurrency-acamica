package lesson4.extra1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Example of AtomicArray usage.
 */
public class Main {

    private static final int TASKS = 10;
    private static final long INITIAL_BALANCE = 1000000L;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(TASKS);
        Account account = new Account(TASKS, INITIAL_BALANCE);

        List<Future<Long>> results = new ArrayList<>(TASKS);
        for (int i=0; i<TASKS; i++) {
            results.add(executor.submit(new BankingTask(i, account)));
        }

        Long finalBalance = INITIAL_BALANCE;
        try {
            for (Future<Long> result : results) {
                finalBalance += result.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
        System.out.println("Total movements were " + account.getMovements());
        System.out.println("Final balance was " + account.getBalance() + " and should be " + finalBalance);
    }
}
