package lesson2.extra1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Example of cached thread pool usage.
 */
public class Main {

    private static long KEEP_ALIVE = 10L;
    private static int TASKS = 30;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setKeepAliveTime(KEEP_ALIVE, TimeUnit.SECONDS);

        for (int i=0; i<TASKS; i++) {
            executor.execute(new MyTask("Task " + i));
        }

        System.out.println("Pool size: " + executor.getPoolSize());

        while (executor.getActiveCount() > 0) {
            sleep(12L, TimeUnit.SECONDS);
        }

        System.out.println("Pool size: " + executor.getPoolSize());

        executor.shutdown();
        System.out.println("All tasks have been executed and executor was terminated.");
    }

    private static void sleep(long time, TimeUnit unit) {
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
