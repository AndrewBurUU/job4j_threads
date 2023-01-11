package ru.job4j.pools.completablefuture;

import java.util.concurrent.*;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] res = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < matrix.length; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            res[i] = new Sums();
            res[i].setRowSum(rowSum);
            res[i].setColSum(colSum);
        }
        return res;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] res = new Sums[n];
        for (int i = 0; i < n; i++) {
            res[i] = new Sums();
            res[i].setRowSum(getTask(matrix, 0, n, i, true).get());
            res[i].setColSum(getTask(matrix, 0, n, i, false).get());
        }
        return res;
    }

    public static CompletableFuture<Integer> getTask(int[][] data, int start, int end, int index, boolean row) {
        return CompletableFuture.supplyAsync(() -> {
                    int sum = 0;
                    for (int i = start; i < end; i++) {
                        if (row) {
                            sum += data[index][i];
                        } else {
                            sum += data[i][index];
                        }
                    }
                    return sum;
                }
        );
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