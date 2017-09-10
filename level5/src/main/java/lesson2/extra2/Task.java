package lesson2.extra2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Runnable {

    private static final AtomicInteger TASK_COUNT = new AtomicInteger(0);
    private Phaser phaser;
    private Executor executor;
    private Integer id, newId;
    private Exchanger<Integer> exchanger;

    public Task(Phaser phaser, Executor executor, Exchanger<Integer> exchanger) {
        this.phaser = phaser;
        this.phaser.register();
        this.executor = executor;
        this.exchanger = exchanger;
        id = newId = TASK_COUNT.incrementAndGet();
    }

    public static int getTaskCount() {
        return TASK_COUNT.get();
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        while (!phaser.isTerminated()) {
            doSomething();
            executor.execute(new Task(phaser, executor, exchanger));
            phaser.arriveAndAwaitAdvance();
        }
        System.out.println("Task " + id + " obtained " + newId + ".");
    }

    private void doSomething() {
        try {
            newId = exchanger.exchange(newId);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}
