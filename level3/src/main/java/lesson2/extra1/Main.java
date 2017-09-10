package lesson2.extra1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Example of lock interruption.
 */
public class Main {

    private static final int TASKS = 5;
    private static final long WAITING_TIME = 4;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(TASKS);
        Printer printer = new Printer();
        Random random = new Random();

        List<Callable<Integer>> tasks = new ArrayList<>(TASKS);
        for (int i=1; i<=TASKS; i++) {
            tasks.add(new Task(i, (long) random.nextInt(5), printer));
        }

        try {
            executor.invokeAll(tasks, WAITING_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
        System.out.println("Printed " + printer.getCompletedJobs() + " jobs.");
    }
}
