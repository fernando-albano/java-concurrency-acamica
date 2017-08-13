package lesson3.extra2;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Long> {

    private Long duration;

    public Task(long duration) {
        this.duration = duration;
    }

    @Override
    public Long call() {
        try {
            TimeUnit.SECONDS.sleep(duration);
            System.out.println(Thread.currentThread().getName() + " with duration " + duration + " seconds, has finished.");
            return duration;
        } catch(InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " with duration " + duration + " seconds, was interrupted.");
            return 0L;
        }
    }
}
