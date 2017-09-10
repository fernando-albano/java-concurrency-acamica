package lesson2.extra1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    private final Lock lock = new ReentrantLock();
    private int completedJobs = 0;

    public void print(Long sleep, String message) {
        try {
            lock.lockInterruptibly();
            TimeUnit.SECONDS.sleep(sleep);
            System.out.println(message + " - Printed " + ++completedJobs + " jobs.");
            lock.unlock();
        } catch (InterruptedException e) {
            System.out.println(message + " was interrupted.");
        }
    }

    public int getCompletedJobs() {
        return completedJobs;
    }
}
