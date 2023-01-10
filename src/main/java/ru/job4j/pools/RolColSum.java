package ru.job4j.pools;

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

    public static Sums[] asyncSum(int[][] matrix) {
        return new Sums[matrix.length];
    }

    public static void main(String[] args) {
        int[][] array = {{5, 1, 6}, {8, 2, 3}, {4, 1, 0}};
        Sums[] res = RolColSum.sum(array);
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