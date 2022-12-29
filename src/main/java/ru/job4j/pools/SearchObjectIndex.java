package ru.job4j.pools;

import java.util.concurrent.*;

public class SearchObjectIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;

    public SearchObjectIndex(T[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    private int search10(T t) {
        for (int i = 0; i < array.length; i++) {
            if (t.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if (from == to) {
            return 0;
        }
        int mid = (from + to) / 2;
        SearchObjectIndex<T> leftSearch = new SearchObjectIndex<>(array, from, mid);
        SearchObjectIndex<T> rightSearch = new SearchObjectIndex<>(array, mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return left >= 0 ? left : right;
    }

    public int search(T[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SearchObjectIndex<T>(array, 0, array.length - 1));
    }
}
