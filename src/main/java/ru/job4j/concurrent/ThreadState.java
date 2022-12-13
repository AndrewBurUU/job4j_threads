package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED
        ) {
            System.out.println(String.format("Thread1 is %s; Thread2 is %s",
                    first.getState(), second.getState()));
        }
        System.out.println("Work is finished");
    }
}