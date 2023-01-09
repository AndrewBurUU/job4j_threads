package ru.job4j.pools.search;

public class MergeSearch<T> {

    public int search(T[] array, T t) {
        if (array.length < 11) {
            return searchObject(array, t, 0, 10);
        }
        return search(array, t, 0, array.length - 1);
    }

    private int search(T[] array, T t, int from, int to) {
        if (from == to) {
            return 0;
        }
        int mid = (from + to) / 2;
        int left = searchObject(array, t, from, mid);
        int right = searchObject(array, t, mid + 1, to);
        return merge(
                left,
                right
        );
    }

    public int merge(int left, int right) {
        return left > -1 ? left : right;
    }

    private int searchObject(T[] array, T t, int from, int to) {
        for (int i = from; i < to; i++) {
            if (t.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

}
