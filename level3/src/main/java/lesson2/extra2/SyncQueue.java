package lesson2.extra2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SyncQueue<E> {

    private final Queue<E> queue = new LinkedList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private final Condition emptyQueueCondition = writeLock.newCondition();

    public int size() {
        try {
            readLock.lockInterruptibly();
            try {
                return queue.size();
            } finally {
                readLock.unlock();
            }
        } catch(InterruptedException e) {
            return -1;
        }
    }

    public void awaitUntilEmpty(long time, TimeUnit timeUnit) throws InterruptedException {
        writeLock.lockInterruptibly();
        emptyQueueCondition.await(time, timeUnit);
    }

    public void enqueue(E element) throws InterruptedException {
        writeLock.lockInterruptibly();
        try {
            queue.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        writeLock.lockInterruptibly();
        if (queue.size() == 0) {
            emptyQueueCondition.signalAll();
        }
        try {
            return queue.poll();
        } finally {
            writeLock.unlock();
        }
    }

    public E head() throws InterruptedException {
        readLock.lockInterruptibly();
        try {
            return queue.peek();
        } finally {
            readLock.unlock();
        }
    }
}
