package ru.job4j.visibility;

import java.io.*;

public class FileOperations {

    private final File file;

    public FileOperations(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (PrintWriter o = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
