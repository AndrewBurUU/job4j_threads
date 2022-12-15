package ru.job4j.atomic;

public class Count {
    private int value;

    public void increment() {
        value++;
    }

    public int get() {
        return value;
    }
}
