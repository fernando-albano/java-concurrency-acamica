package lesson4.extra2;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class AccountSet {

    private AtomicReferenceArray<Balance> balances;

    public AccountSet(int size, long initialBalance) {
        this.balances = new AtomicReferenceArray<>(size);
        for (int i=0; i<size; i++) {
            this.balances.set(i, new Balance(initialBalance));
        }
    }

    public int size() {
        return balances.length();
    }

    public void add(int accountId, long amount) {
        balances.getAndUpdate(accountId, balance -> Balance.add(balance, amount));
    }

    public void subtract(int accountId, long amount) {
        balances.getAndUpdate(accountId, balance -> Balance.subtract(balance, amount));
    }

    public Balance getBalance(int accountId) {
        return balances.get(accountId);
    }
}
