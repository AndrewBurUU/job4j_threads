package ru.job4j.pools.completablefuture;

import java.util.*;
import java.util.concurrent.*;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] rsl = new Sums[n];
        for (int row = 0; row < n; row++) {
            rsl[row] = getSums(matrix, row);
        }
        return rsl;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] rsl = new Sums[n];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int row = 0; row < n; row++) {
            futures.put(row, getTask(matrix, row));
        }
        for (int i = 0; i < rsl.length; i++) {
            rsl[i] = futures.get(i).get();
        }
        return rsl;
    }

    private static Sums getSums(int[][] matrix, int row) {
        int rowRsl = 0;
        int colRsl = 0;
        for (int cell = 0; cell < matrix.length; cell++) {
            rowRsl += matrix[row][cell];
            colRsl += matrix[cell][row];
        }
        return new Sums(rowRsl, colRsl);
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int row) {
        return CompletableFuture.supplyAsync(() -> getSums(matrix, row));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] array = {{5, 1, 6}, {8, 2, 3}, {4, 1, 0}};
        /** Sequence
        * Sums[] res = RolColSum.sum(array);*/
        /** Async */
        Sums[] res = RolColSum.asyncSum(array);
        System.out.println("RowSum:");
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i].getRowSum());
        }
        System.out.println("ColSum:");
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i].getColSum());
        }
    }

}