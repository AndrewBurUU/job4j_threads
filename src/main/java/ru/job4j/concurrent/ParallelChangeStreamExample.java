package ru.job4j.concurrent;

import java.util.stream.IntStream;

public class ParallelChangeStreamExample {
    public static void main(String[] args) {
        IntStream parallel = IntStream.range(1, 100).parallel();
        System.out.println(parallel.isParallel());
        IntStream sequential = parallel.sequential();
        System.out.println(sequential.isParallel());
    }
}
