package ru.job4j.visibility;

import java.io.*;
import java.util.function.*;

public class FileOperations {

    private final File file;

    public FileOperations(File file) {
        this.file = file;
    }

    public synchronized String content(Predicate<Integer> filter) throws IOException {
        BufferedReader i = new BufferedReader(new FileReader(file));
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            if (filter.test(data)) {
                output += (char) data;
            }
        }
        return output;
    }

    public synchronized void saveContent(String content) throws IOException {
        PrintWriter o = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ));
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }

}
