package ru.job4j.cash;

import net.jcip.annotations.*;

import java.util.*;

@ThreadSafe
public class AccountStorage {

    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        Optional<Account> res = Optional.empty();
        Account account = accounts.get(id);
        if (account != null) {
            res = Optional.of(account);
        }
        return res;
    }

    public synchronized boolean transfer(int fromId, int toId, int sum) {
        boolean res = false;
        Optional<Account> accountFrom = getById(fromId);
        Optional<Account> accountTo = getById(toId);
        if (accountFrom.isPresent() && accountTo.isPresent()
                && accountFrom.get().amount() >= sum
        ) {
            update(new Account(fromId, accountFrom.get().amount() - sum));
            update(new Account(toId, accountTo.get().amount() + sum));
            res = true;
        }
        return true;
    }
}
