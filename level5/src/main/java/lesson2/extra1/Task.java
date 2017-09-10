package lesson2.extra1;

import java.util.concurrent.Executor;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Runnable {

    private static final AtomicInteger TASK_COUNT = new AtomicInteger(0);
    private Phaser phaser;
    private Executor executor;

    public Task(Phaser phaser, Executor executor) {
        this.phaser = phaser;
        this.phaser.register();
        this.executor = executor;
        TASK_COUNT.incrementAndGet();
    }

    public static int getTaskCount() {
        return TASK_COUNT.get();
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        while (!phaser.isTerminated()) {
            doSomething();
            executor.execute(new Task(phaser, executor));
            phaser.arriveAndAwaitAdvance();
        }
    }

    private void doSomething() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}
