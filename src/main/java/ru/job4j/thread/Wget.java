package ru.job4j.thread;

import java.io.*;
import java.net.*;
import java.time.*;
import java.time.temporal.*;

public class Wget implements Runnable {

    private final static int BYTESPERSECOND = 1048576;
    private final String url;
    private final int speed;
    private final String downLoadFileName;

    public Wget(String url, int speed, String downLoadFileName) {
        this.url = url;
        this.speed = speed;
        this.downLoadFileName = downLoadFileName;
    }

    public static boolean checkUrl(String s) {
        return s != null && s.matches("^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    public static void argumentsValidate(String[] arguments) {
        if (arguments.length != 3) {
            throw new IllegalArgumentException("Incorrect parameter numbers.");
        }
        String url = arguments[0];
        if (!checkUrl(url)) {
            throw new IllegalArgumentException(String.format("Incorrect URL", url));
        }
        File file = new File(arguments[2]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(downLoadFileName)) {
            byte[] dataBuffer = new byte[BYTESPERSECOND];
            int bytesRead;
            int downloadData = 0;
            LocalDateTime timeStartDownLoad = LocalDateTime.now();
            while ((bytesRead = in.read(dataBuffer, 0, BYTESPERSECOND)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                downloadData += bytesRead;
                if (downloadData >= BYTESPERSECOND) {
                    long timeDifference = ChronoUnit.MILLIS.between(timeStartDownLoad, LocalDateTime.now());
                    if (timeDifference < 1000) {
                        Thread.sleep(1000 - timeDifference);
                    }
                    downloadData = 0;
                    timeStartDownLoad = LocalDateTime.now();
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
        String downLoadFileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, downLoadFileName));
        wget.start();
        wget.join();
    }
}
