package lesson2.extra2;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Complex example using Read and Write Locks.
 */
public class Main {

    private static final int COUNTERS = 5;
    private static final int OBJECTS = 50;
    private static final long WAITING_TIME = 4;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(COUNTERS + 1);
        SyncQueue<String> queue = new SyncQueue<>();

        for (int i=1; i<=COUNTERS; i++) {
            executor.execute(new Counter(queue));
        }
        executor.execute(new Remover(queue));

        try {
            for (int i=1; i<=OBJECTS; i++) {
                queue.enqueue("Object " + i);
            }

            System.out.println("Waiting until the queue is empty..");
            queue.awaitUntilEmpty(WAITING_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Elements left in the queue " + queue.size() + ".");
        System.out.println("Terminating executor..");
        executor.shutdownNow();
    }
}
