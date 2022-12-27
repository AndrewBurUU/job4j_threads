package ru.job4j.pool;

import ru.job4j.wait.*;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private static final int COUNT = 2;
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(COUNT);
    private int size;

    public ThreadPool() {
        size = Runtime.getRuntime().availableProcessors();
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(thread -> thread.interrupt());
    }
}