package ru.job4j.thread;

import java.io.*;
import java.net.*;
import java.time.*;
import java.time.temporal.*;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    public static boolean checkUrl(String s) {
        return s != null && s.matches("^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    public static void argumentsValidate(String[] arguments) {
        if (arguments.length != 2) {
            throw new IllegalArgumentException("Incorrect parameter numbers.");
        }
        String url = arguments[0];
        if (!checkUrl(url)) {
            throw new IllegalArgumentException(String.format("Incorrect URL", url));
        }
    }

    @Override
    public void run() {
        LocalDateTime timeStartDownLoad = LocalDateTime.now();
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long timeDifference = ChronoUnit.MILLIS.between(timeStartDownLoad, LocalDateTime.now());
                if (timeDifference < speed) {
                    Thread.sleep(timeDifference);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        argumentsValidate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
