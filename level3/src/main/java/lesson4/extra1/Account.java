package lesson4.extra1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public class Account {

    private static final Long MAX_MOVEMENTS = 1000000L;

    private AtomicLong balance;
    private AtomicLong movements;
    private AtomicLongArray taskBalance;
    private AtomicBoolean movementsAllowed;

    public Account(int tasks, Long balance) {
        this.balance = new AtomicLong(balance);
        this.movements = new AtomicLong();
        this.movementsAllowed = new AtomicBoolean(true);

        this.taskBalance = new AtomicLongArray(tasks);
        for (int i=0; i<tasks; i++) {
            this.taskBalance.set(i, 0);
        }
    }

    public Long getBalance() {
        return balance.get();
    }

    public Long getTaskBalance(int taskId) {
        return taskBalance.get(taskId);
    }

    public Long getMovements() {
        return movements.get();
    }

    public boolean areMovementsAllowed() {
        return movementsAllowed.get();
    }

    public void add(int taskId, long amount) {
        balance.addAndGet(amount);
        taskBalance.addAndGet(taskId, amount);
        Long currentMovements = movements.incrementAndGet();
        movementsAllowed.compareAndSet(currentMovements >= MAX_MOVEMENTS, false);
    }

    public void subtract(int taskId, long amount) {
        balance.addAndGet(amount * -1);
        taskBalance.addAndGet(taskId, amount * -1);
        Long currentMovements = movements.incrementAndGet();
        movementsAllowed.compareAndSet(currentMovements >= MAX_MOVEMENTS, false);
    }
}
