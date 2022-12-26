package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int current;
        int next;
        do {
            current = count.get();
            next = current + 1;
        } while (!count.compareAndSet(current, next));
        count.set(next);
    }

    public int get() {
        int current;
        int next;
        do {
            current = count.get();
            next = current;
        } while (!count.compareAndSet(current, next));
        return next;
    }
}