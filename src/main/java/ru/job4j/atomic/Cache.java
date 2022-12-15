package ru.job4j.atomic;

public final class Cache {
    private static Cache cache;

    public static synchronized Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();
        Thread first = new Thread(
                () -> cache.instOf()
        );
        Thread second = new Thread(
                () -> cache.instOf()
        );
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(cache);
    }
}
