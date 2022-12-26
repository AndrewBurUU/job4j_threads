package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int temp;
        int ref;
        do {
            ref = count.get() + 1;
            temp = ref;
        } while (!count.compareAndSet(ref, temp));
        /**throw new UnsupportedOperationException("Count is not impl.");*/
    }

    public int get() {
        int temp;
        int ref;
        do {
            ref = count.get();
            if (ref == 0) {
                throw new UnsupportedOperationException("Count is not impl.");
            }
            temp = ref;
        } while (!count.compareAndSet(ref, temp));
        return ref;
    }
}