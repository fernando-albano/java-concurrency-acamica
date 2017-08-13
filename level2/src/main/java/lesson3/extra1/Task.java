package lesson3.extra1;

import java.util.concurrent.Callable;

public class Task implements Callable<Long> {

    private Long num;

    public Task(long num) {
        this.num = num;
    }

    @Override
    public Long call() {
        try {
            return veryInefficientFibonacci(num);
        } catch(InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
            return 0L;
        }
    }

    public long veryInefficientFibonacci(long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        } else {
            if (n == 0 || n == 1) {
                return n;
            } else {
                return veryInefficientFibonacci(n - 2) + veryInefficientFibonacci(n - 1);
            }
        }
    }
}
