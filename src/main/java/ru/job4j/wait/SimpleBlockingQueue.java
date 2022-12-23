package ru.job4j.wait;

import javax.annotation.concurrent.*;
import java.util.*;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private int count;

    public synchronized void offer(T value) {
            queue.add(value);
            this.notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
       T res = queue.poll();
       this.notifyAll();
       return res;
    }
}
