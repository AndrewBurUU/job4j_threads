package ru.job4j.visibility;

import java.io.*;
import java.util.function.*;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String content(Predicate<Integer> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = i.read()) != -1) {
                if (filter.test(data)) {
                    output.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
