package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    private static final char[] PROCESS = new char[]{'-', '|', '/', '\\'};

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r loading: " + PROCESS[count++]);
                if (count == PROCESS.length) {
                    count = 0;
                }
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000); /** симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt();
    }
}
