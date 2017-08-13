package lesson2.extra2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

public class MyExecutor implements Executor {

    private Semaphore queueSize, queueMutex;
    private Queue<Runnable> taskQueue;
    private MyThreadFactory threadFactory;

    public MyExecutor(int poolSize) {
        this.queueSize = new Semaphore(0);
        this.queueMutex = new Semaphore(1);
        this.taskQueue = new LinkedList<>();
        this.threadFactory = new MyThreadFactory("thread-group", "worker-");
        this.init(poolSize);
    }

    private void init(int poolSize) {
        for (int i=0; i<poolSize; i++) {
            PoolWorkerTask task = new PoolWorkerTask(taskQueue, queueSize, queueMutex);
            threadFactory.newThread(task).start();
        }
    }

    @Override
    public void execute(Runnable task) {
        try {
            queueMutex.acquire();
            taskQueue.add(task);
            queueMutex.release();
            queueSize.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void interrupt() {
        threadFactory.getThreadGroup().interrupt();
    }
}
