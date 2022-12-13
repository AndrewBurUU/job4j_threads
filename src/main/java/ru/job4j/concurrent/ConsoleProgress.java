package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    private static final char[] PROCESS = new char[]{'-', '|', '/', '\\'};

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r loading: " + PROCESS[count++]);
                if (count == 4) {
                    count = 0;
                }
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(1000); /** симулируем выполнение параллельной задачи в течение 1 секунды. */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }
}
