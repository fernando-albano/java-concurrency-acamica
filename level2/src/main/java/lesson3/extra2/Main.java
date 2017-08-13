package lesson3.extra2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Example of Callable interruption using Future interface.
 */
public class Main {

    private static final int TASKS = 5;
    private static final long WAITING_TIME = 1;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(TASKS);
        Random random = new Random();

        List<Callable<Long>> tasks = new ArrayList<>(TASKS);
        for (int i=0; i<TASKS; i++) {
            tasks.add(new Task(random.nextInt(10)));
        }

        try {
            Long result = executor.invokeAny(tasks, WAITING_TIME, TimeUnit.SECONDS);
            System.out.println("The first result was: " + result);
        } catch (TimeoutException e) {
            System.out.println("Timeout of " + WAITING_TIME + " seconds was exceeded.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("Executor was successfully terminated.");
    }
}
