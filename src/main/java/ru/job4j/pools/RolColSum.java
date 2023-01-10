package ru.job4j.pools;

import java.util.concurrent.*;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] res = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                sum += matrix[i][j];
            }
            res[i] = new Sums();
            res[i].setRowSum(sum);
        }
        for (int j = 0; j < matrix.length; j++) {
            int sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                sum += matrix[i][j];
            }
           res[j].setColSum(sum);
        }
        return res;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] res = new Sums[n];
        for (int i = 0; i < n; i++) {
            res[i] = new Sums();
        }
        for (int i = 0; i < n; i++) {
            res[i].setRowSum(getRowSum(matrix, 0, n, i).get());
            res[i].setColSum(getColSum(matrix, 0, n, i).get());
        }
        return res;
    }

    public static CompletableFuture<Integer> getRowSum(int[][] data, int startCol, int endCol, int row) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int j = startCol; j < endCol; j++) {
                sum += data[row][j];
                }
                return sum;
            }
       );
    }

    public static CompletableFuture<Integer> getColSum(int[][] data, int startRow, int endRow, int col) {
        return CompletableFuture.supplyAsync(() -> {
                    int sum = 0;
                    for (int i = startRow; i < endRow; i++) {
                        sum += data[i][col];
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