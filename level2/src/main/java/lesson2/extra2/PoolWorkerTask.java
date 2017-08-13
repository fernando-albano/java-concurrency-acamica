package lesson2.extra2;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class PoolWorkerTask implements Runnable {

    private Queue<Runnable> taskQueue;
    private Semaphore queueSize, queueMutex;
    private Runnable currentTask;
    private int count;

    public PoolWorkerTask(Queue<Runnable> taskQueue, Semaphore queueSize, Semaphore queueMutex) {
        this.taskQueue = taskQueue;
        this.queueSize = queueSize;
        this.queueMutex = queueMutex;
        this.count = 0;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                queueSize.acquire();
                queueMutex.acquire();
                currentTask = taskQueue.poll();
                queueMutex.release();
                currentTask.run();
                count++;
            }
        } catch(InterruptedException e) {
        } finally {
            System.out.println(Thread.currentThread().getName() + " was interrupted. Executed " + count + " tasks.");
        }
    }
}
