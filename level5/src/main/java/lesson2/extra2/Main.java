package lesson2.extra2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Example of Phaser and Exchanger usage.
 */
public class Main {

    private static final int MAX_PHASES = 4;
    private static final int TOTAL_TASKS = 1 << MAX_PHASES; // 2^PHASES

    public static void main(String[] args) {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        final Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("Completed phase " + phase + " with " + registeredParties + " registered tasks.");
                return phase >= MAX_PHASES - 1 || registeredParties == 0;
            }
        };

        Exchanger<Integer> exchanger = new Exchanger<>();
        executor.execute(new Task(phaser, executor, exchanger));
        executor.execute(new Task(phaser, executor, exchanger));

        int phase = 0;
        while (!phaser.isTerminated()) {
            phase = phaser.awaitAdvance(phase);
        }
        executor.shutdown();

        if (phaser.isTerminated()) {
            System.out.println("Phaser was terminated.");
        }
        System.out.println("Created " + Task.getTaskCount() + " of " + TOTAL_TASKS + " tasks.");
        System.out.println("Launched " + executor.getTaskCount() + " of " + TOTAL_TASKS + " tasks.");
        System.out.println("Arrived " + phaser.getArrivedParties() + " of " + TOTAL_TASKS + " tasks.");
    }
}
