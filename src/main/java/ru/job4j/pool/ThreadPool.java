package ru.job4j.pool;

import ru.job4j.wait.*;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private static final int SIZE = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(SIZE);

    public ThreadPool() {
        for (int i = 0; i < SIZE; i++) {
            Thread thread = new Thread(
                    () -> {
                        try {
                            tasks.poll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
            );
            thread.start();
            threads.add(thread);
        }

    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(thread -> thread.interrupt());
    }
}