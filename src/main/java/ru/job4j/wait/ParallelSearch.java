package ru.job4j.wait;

public class ParallelSearch {
    public static void main(String[] args) throws InterruptedException {
        int count = 3;
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(count);
        final Thread consumer = new Thread(
                () -> {
                    try {
                        while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                            System.out.println(queue.poll());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index < 3; index++) {
                        try {
                            queue.offer(index);
                            System.out.println("Provider add: " + index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        ).start();
        consumer.interrupt();
    }
}