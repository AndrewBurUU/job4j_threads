package ru.job4j.pools;

import java.util.concurrent.*;

class SearchObjectIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T object;

    public SearchObjectIndex(T[] array, T object, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.object = object;
    }

    private int search10(T t) {
        for (int i = from; i < to; i++) {
            if (t.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return search10(object);
        }
        int mid = (from + to) / 2;
        SearchObjectIndex<T> leftSearch = new SearchObjectIndex<>(array, object, from, mid);
        SearchObjectIndex<T> rightSearch = new SearchObjectIndex<>(array, object, mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return Math.max(left, right);
    }

    public int search(T[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SearchObjectIndex<T>(array, object, 0, array.length - 1));
    }
}



