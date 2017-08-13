package lesson3.extra1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Example of invokeAll method usage.
 */
public class Main {

    private static final long N = 5000L;
    private static final int TASKS = 5;
    private static final long WAITING_TIME = 5;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(TASKS);

        List<Callable<Long>> tasks = new ArrayList<>(TASKS);
        for (int i=0; i<TASKS; i++) {
            tasks.add(new Task(N));
        }

        try {
            List<Future<Long>> results = executor.invokeAll(tasks, WAITING_TIME, TimeUnit.SECONDS);
            for (Future<Long> result : results) {
                if (!result.isDone()) {
                    result.cancel(true);
                }
            }

            while (executor.getActiveCount() > 0) {
                Thread.sleep(50L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("Executor was successfully terminated.");
    }
}
