package lesson4.extra2;

public class Balance {

    private Long balance;
    private Long movements;

    public Balance(long balance) {
        this(balance, 0L);
    }

    private Balance(long balance, long movements) {
        this.balance = balance;
        this.movements = movements;
    }

    public static Balance add(Balance balance, Long amount) {
        return new Balance(balance.balance + amount, balance.movements + 1);
    }

    public static Balance subtract(Balance balance, Long amount) {
        return new Balance(balance.balance - amount, balance.movements + 1);
    }

    public static Balance wrongAdd(Balance balance, Long amount) {
        balance.movements++;
        balance.balance += amount;
        return balance;
    }

    public static Balance wrongSubtract(Balance balance, Long amount) {
        balance.movements++;
        balance.balance -= amount;
        return balance;
    }

    public Long getBalance() {
        return balance;
    }

    public Long getMovements() {
        return movements;
    }
}
