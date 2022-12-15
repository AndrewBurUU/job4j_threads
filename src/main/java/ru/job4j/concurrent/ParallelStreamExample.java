package ru.job4j.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.parallelStream();
        System.out.println(stream.isParallel());
        Optional<Integer> multiplication = stream.reduce((a,b) -> a * b);
        System.out.println(multiplication.get());
        System.out.println("peek:");
        list.stream().parallel().peek(System.out::println).collect(Collectors.toList());
        System.out.println("forEach:");
        list.stream().parallel().forEach(System.out::println);
        System.out.println("forEachOrdered:");
        list.stream().parallel().forEachOrdered(System.out::println);
    }
}
